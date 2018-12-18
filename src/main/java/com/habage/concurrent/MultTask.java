package com.habage.concurrent;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuz
 */
public class MultTask {

    private static ThreadFactory factory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ThreadPool thread: " + integer.getAndIncrement());
        }
    };

    private static  ExecutorService pool = new ThreadPoolExecutor(10, 10, 0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), factory);

    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);

    private List<ListenableFuture<Integer>> featureList;

    public MultTask() {
        featureList = Lists.newArrayList();
    }

    public MultTask addTask(Callable<Integer> callable) {
        featureList.add(service.submit(callable));
        return this;
    }

    public List<Integer> get() throws ExecutionException, InterruptedException {
        ListenableFuture<List<Integer>> listenableFutures = Futures.successfulAsList(featureList);
        return listenableFutures.get();
    }

    public static MultTask newInstance() {
        return new MultTask();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Mult Task...");

        List<Integer> values = MultTask.newInstance().addTask(() -> {
            System.out.println("start1");
            Thread.sleep(1000);
            System.out.println("call future 1.");
            return 1;
        }).addTask(() -> {
            System.out.println("start2");
            Thread.sleep(1000);
            System.out.println("call future 2.");
            return 2;
        }).get();
        System.out.println(values);

        System.out.println("finished!!");

        pool.shutdown();
    }
}

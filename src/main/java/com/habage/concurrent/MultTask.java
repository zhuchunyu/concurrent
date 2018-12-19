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

    private static  ExecutorService pool;

    private  ListeningExecutorService service;

    private List<ListenableFuture<Integer>> featureList;

    public MultTask() {
        this(10);
    }

    public MultTask(int concurrency) {
        pool = new ThreadPoolExecutor(concurrency, concurrency, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), factory);
        service = MoreExecutors.listeningDecorator(pool);
        featureList = Lists.newArrayList();
    }

    public MultTask addTask(Callable<Integer> callable) {
        featureList.add(service.submit(callable));
        return this;
    }

    public List<Integer> get() throws ExecutionException, InterruptedException {
        ListenableFuture<List<Integer>> listenableFutures = Futures.successfulAsList(featureList);
        List<Integer> integers = listenableFutures.get();
        pool.shutdown();
        return integers;
    }

    public static MultTask newInstance() {
        return new MultTask(2);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Mult Task...");

        List<Integer> values = MultTask.newInstance().addTask(() -> {
            System.out.println("start1");
            Thread.sleep(100);
            System.out.println("call future 1.");
            return 1;
        }).addTask(() -> {
            System.out.println("start2");
            Thread.sleep(100);
            System.out.println("call future 2.");
            return 2;
        }).addTask(() -> {
            System.out.println("start3");
            Thread.sleep(100);
            System.out.println("call future 3.");
            return 3;
        }).addTask(() -> {
            System.out.println("start4");
            Thread.sleep(100);
            System.out.println("call future 4.");
            return 4;
        }).addTask(() -> {
            System.out.println("start5");
            Thread.sleep(100);
            System.out.println("call future 5.");
            return 5;
        }).get();
        System.out.println(values);

        System.out.println("finished!!");
    }
}

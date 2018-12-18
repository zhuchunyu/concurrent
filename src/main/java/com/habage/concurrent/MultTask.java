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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Mult Task...");

        ExecutorService pool = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), factory);

        ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);

        ListenableFuture future1 = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws InterruptedException {
                System.out.println("start1");
                Thread.sleep(1000);
                System.out.println("call future 1.");
                return 1;
            }
        });

        ListenableFuture future2 = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws InterruptedException {
                System.out.println("start2");
                Thread.sleep(1000);
                System.out.println("call future 2.");
                //	   throw new RuntimeException("----call future 2.");
                return 2;
            }
        });

        List<ListenableFuture<Integer>> features = Lists.newArrayList();
        features.add(future1);
        features.add(future2);

        ListenableFuture<List<Integer>> listListenableFuture = Futures.successfulAsList(features);
        listListenableFuture.get();

        System.out.println("finished!!");

        pool.shutdown();
    }
}

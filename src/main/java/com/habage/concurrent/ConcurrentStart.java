package com.habage.concurrent;

import java.util.concurrent.*;

/**
 * Hello world!
 */
public class ConcurrentStart {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        new Task1().start();

        new Thread(new Task2()).start();

        ExecutorService es = Executors.newFixedThreadPool(1);
        Future future = es.submit(new Task3());
        try {
            System.out.println("Calculate Completed Sum：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}

class Task1 extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Started");
    }
}

class Task2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Started");
    }
}

class Task3 implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " Started");
        //求和
        return 1 + 1;
    }
}


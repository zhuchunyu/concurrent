package com.habage.test;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RRemoteService;
import org.redisson.config.Config;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Lock {

    public static void main(String[] args) throws Exception {
        System.out.println("Lock");
        Config config = new Config();

        config.useSingleServer().setDatabase(1).setAddress("redis://192.168.43.227:6379");
        Redisson redisson = (Redisson) Redisson.create(config);

        RAtomicLong atomicLong = redisson.getAtomicLong("onekey");
        long onekey = atomicLong.incrementAndGet();
        System.out.println(onekey);

        RLock lock = redisson.getLock("mylock");

        try {
            lock.lock();
            System.out.println("1 get lock");
        } finally {
            lock.unlock();
        }

        RMap<String, Object> mymap = redisson.getMap("mymap");
        mymap.put("one", 1);

        RRemoteService remoteService = redisson.getRemoteService();
        BlogService blogService = new BlogServiceImpl();
        remoteService.register(BlogService.class, blogService);


        Set<String> set = new HashSet<>();

        ExecutorService es = Executors.newCachedThreadPool();
        ThreadPoolExecutor tpool = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, null);
        tpool.prestartAllCoreThreads();

        //redisson.shutdown();
    }
}

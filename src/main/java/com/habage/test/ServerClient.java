package com.habage.test;

import org.redisson.Redisson;
import org.redisson.api.RRemoteService;
import org.redisson.config.Config;

import java.util.List;

public class ServerClient {
    public static void main(String[] args) {
        Config config = new Config();

        config.useSingleServer().setDatabase(1).setAddress("redis://192.168.43.227:6379");
        Redisson redisson = (Redisson) Redisson.create(config);

        RRemoteService remoteService = redisson.getRemoteService();

        BlogService blogService = remoteService.get(BlogService.class);
        List<String> blogs = blogService.blogs();
        System.out.println(blogs);

        redisson.shutdown();
    }
}

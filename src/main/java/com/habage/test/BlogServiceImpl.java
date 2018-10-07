package com.habage.test;

import java.util.Arrays;
import java.util.List;

public class BlogServiceImpl implements BlogService {
    @Override
    public List<String> blogs() {
        System.out.println("invoke this...");
        return Arrays.asList("one", "two", "three");
    }
}

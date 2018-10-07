package com.habage.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OneTest {
    public static void main(String[] args) {
        boolean matches = Pattern.matches("token=([\\w\\d]{0,2})", "token=22");
        System.out.println(matches);

        Pattern pattern = Pattern.compile("token=([\\w\\d]{0,2})");
        Matcher matcher = pattern.matcher("token=22;jack=23;token=33;name=jack");
        System.out.println(matcher.groupCount());
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }
    }
}

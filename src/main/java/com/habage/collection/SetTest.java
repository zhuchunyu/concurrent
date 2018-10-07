package com.habage.collection;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {
        Set<String> hs = new HashSet<>();
        hs.add("世界军事");
        hs.add("兵器知识");
        hs.add("舰船知识");
        hs.add("汉和防务");
        System.out.println(hs);
        // [舰船知识, 世界军事, 兵器知识, 汉和防务]
        for (Object h : hs) {
            System.out.println(h);
        }

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("one");
        System.out.println(treeSet);

        class A implements Comparable<A> {

            @Override
            public int compareTo(A o) {
                return 0;
            }
        }
        TreeSet<A> set = new TreeSet<>();
        set.add(new A());
        set.add(new A());
        set.add(new A());
        System.out.println(set);

        Codec MYSQL_CODEC = new MySQLCodec(MySQLCodec.Mode.STANDARD);
        String s = ESAPI.encoder().encodeForSQL(MYSQL_CODEC, "onaa#x'");
        System.out.println(s);
    }
}

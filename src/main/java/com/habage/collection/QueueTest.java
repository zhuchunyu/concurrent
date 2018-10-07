package com.habage.collection;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Queue");
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer("one");
        priorityQueue.offer("two");
        priorityQueue.offer("pell");
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue);

        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("3");
        blockingQueue.put("5");
        blockingQueue.put("2");
        System.out.println(blockingQueue);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue);

        LinkedList<String> queue = new LinkedList<>();
        queue.push("one");
        System.out.println(queue);
    }
}

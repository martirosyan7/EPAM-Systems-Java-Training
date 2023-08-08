package com.efimchick.ifmo.collections;

import java.util.*;

class MedianQueue extends LinkedList<Integer> implements Queue<Integer> {

    LinkedList<Integer> queue = new LinkedList<>();

    Queue<Integer> sortQueue()
    {
        ArrayList<Integer> arr = new ArrayList<>();
        while(queue.isEmpty() == false)
        {
            arr.add(queue.poll());
        }
        Collections.sort(arr);
        for(int i=0;i<arr.size();i++)
        {
            queue.add(arr.get(i));
        }
        return queue;
    }

    public int getMedian() {
        LinkedList<Integer> integerQueue = (LinkedList<Integer>) sortQueue();
        integerQueue = (LinkedList<Integer>) integerQueue.clone();
        int index = (int) Math.floor(integerQueue.size() / 2.0);
        for (int i = 0; i < index; i++) {
            integerQueue.poll();
        }
        return integerQueue.poll();
    }
    public LinkedList<Integer> getQueue() {
        Queue<Integer> result = new LinkedList<>();
        LinkedList<Integer> firstHalf = new LinkedList<>();
        Queue<Integer> integerQueue = (Queue<Integer>) sortQueue();
        int index = (int) Math.floor((integerQueue.size() -1) / 2.0);
        for (int i = 0; i < index; i++) {
            firstHalf.offer(integerQueue.poll());
        }
        result.offer(integerQueue.poll());

        while(firstHalf.size() > 1 || integerQueue.size() > 1) {
            if (firstHalf.size() >= integerQueue.size()) {
                result.offer(firstHalf.pollLast());
                result.offer(integerQueue.poll());
                continue;
            }
            result.offer(integerQueue.poll());
            result.offer(firstHalf.pollLast());
        }
        if (!firstHalf.isEmpty()) result.offer(firstHalf.poll());
        if (!integerQueue.isEmpty()) result.offer(integerQueue.poll());
        return (LinkedList<Integer>) result;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean offer(Integer integer) {
        queue.offer(integer);
        queue = getQueue();

        return true;
    }

    @Override
    public Integer poll() {
        int value = queue.poll();
        return value;
    }

    @Override
    public Integer peek() {
        return queue.peek();
    }
}

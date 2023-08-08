package com.epam.rd.autocode.hashtableopen816;

import java.util.stream.IntStream;

public class HashtableOpen8to16Impl implements HashtableOpen8to16 {
    private static final int MAX_CAPACITY = 16;
    private final double loadFactor;
    private static final int MULTIPLIER = 2;
    private static final int DIVISOR = 2;
    private static final int RATIO = 4;
    private ListNode[] table;
    private int sizeElements;
    private int searchCounter;

    public HashtableOpen8to16Impl(int initialCapacity, double loadFactor) {
        table = new ListNode[initialCapacity];
        this.loadFactor = loadFactor;
    }

    @Override
    public void insert(int key, Object value) {
        int bucket = getBucket(key);
        ListNode list = table[bucket];
        while (list != null && list.notMarkedAsRemoved()) {
            if (list.getKey().equals(key)) {
                sizeElements--;
                break;
            } else bucket = getLinearBucket(table, bucket, key);
            list = null;
        }
        if (sizeElements >= loadFactor * size()) {
            resize(size() * MULTIPLIER);
            bucket = getBucket(key);
            bucket = getLinearBucket(table, bucket, key);
        }
        addNewNode(key, value, bucket);
        sizeElements++;
    }

    private void addNewNode(int key, Object value, int bucket) {
        ListNode newNode = new ListNode();
        newNode.setKey(key);
        newNode.setValue(value);
        table[bucket] = newNode;
    }

    @Override
    public Object search(int key) {
        int bucket = getBucket(key);
        searchCounter = 0;
        ListNode listNode = checkBucketForListNode(key, bucket);
        if (listNode != null) return listNode.getValue();
        else return null;
    }

    @Override
    public void remove(int key) {
        int bucket = getBucket(key);
        searchCounter = 0;
        ListNode listNode = checkBucketForListNode(key, bucket);
        if (listNode != null) {
            removeNode(listNode);
            sizeElements--;
            decreaseSize();
        }
    }

    private void removeNode(ListNode listNode) {
        listNode.setKey(null);
        listNode.setValue(null);
        listNode.markedAsRemoved();
    }

    private void decreaseSize() {
        if (sizeElements != 0 && sizeElements * RATIO <= size()) {
            resize(size() / DIVISOR);
        }
    }

    private ListNode checkBucketForListNode(int key, int bucket) {
        if (searchCounter == table.length) return null;
        searchCounter++;
        if (table[bucket] != null) {
            if (table[bucket].getKey() == key && table[bucket].notMarkedAsRemoved()) {
                return table[bucket];
            } else {
                return checkBucketForListNode(key, indexLinearIncrease(bucket));
            }
        }
        return null;
    }

    private int indexLinearIncrease(int bucket) {
        bucket++;
        if (bucket != table.length) return bucket;
        else return 0;
    }

    @Override
    public int size() {
        return table.length;
    }

    @Override
    public int[] keys() {
        int[] keys = new int[size()];
        for (int i = 0; i < keys.length; i++) {
            if (table[i] != null && table[i].notMarkedAsRemoved())
                keys[i] = table[i].getKey();
        }
        return keys;
    }

    private int getBucket(Object key) {
        return (Math.abs(key.hashCode())) % size();
    }

    private void resize(int size) {
        ListNode[] newTable = new ListNode[size];
        addIllegalStateException(newTable);
        for (ListNode list : table) {
            if (list != null && list.notMarkedAsRemoved()) {
                int bucket = (Math.abs(list.getKey().hashCode())) % newTable.length;
                bucket = getLinearBucket(newTable, bucket, list.getKey());
                newTable[bucket] = list;
            }
        }
        table = newTable;
    }

    private void addIllegalStateException(ListNode[] newTable) {
        if (newTable.length > MAX_CAPACITY)
            throw new IllegalStateException("IllegalStateException");
    }

    private int getLinearBucket(ListNode[] table, int bucket, int key) {
        for (int i = bucket; i < table.length; i++) {
            if (table[i] == null) {
                bucket = i;
                break;
            }
            if (i == table.length - 1) {
                bucket = IntStream.range(0, bucket)
                        .filter(j -> table[j] == null)
                        .findFirst()
                        .orElse(bucket);
            }
            if (table[i] != null && table[i].getKey() == key && table[i].notMarkedAsRemoved()) {
                bucket = i;
                sizeElements--;
                break;
            }
        }
        return bucket;
    }
}

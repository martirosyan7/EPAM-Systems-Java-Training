package com.efimchick.ifmo.collections;

import java.util.*;

class PairStringList extends ArrayList<String> implements List<String> {

    List<String> list = new ArrayList<>();
    List<String> pairStringList = new ArrayList<>();

    @Override
    public int size() {
        return list.size() * 2;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<String> iterator() {
        convertToPairString();
        return pairStringList.iterator();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        convertToPairString();
        return pairStringList.toArray(a);
    }

    @Override
    public boolean add(String s) {
        if (list.add(s)) return true;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (list.remove(o)) return true;
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        list.addAll(c);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        list.addAll((int) Math.ceil(index/2.0), c);
        return true;
    }

    @Override
    public String get(int index) {
        return list.get((int) Math.floor(index/2.0));
    }

    @Override
    public String set(int index, String element) {
        return list.set((int) Math.floor(index/2.0), element);
    }

    @Override
    public void add(int index, String element) {
        list.add((int) Math.ceil(index/2.0), element);
    }

    @Override
    public String remove(int index) {
        return list.remove((int) Math.floor(index/2.0));
    }

    @Override
    public ListIterator<String> listIterator() {
        convertToPairString();
        return pairStringList.listIterator();
    }

    public void convertToPairString() {
        pairStringList.clear();
        for (String str : list) {
            pairStringList.add(str);
            pairStringList.add(str);
        }
    }
}

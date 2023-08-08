package com.epam.rd.autocode.iterator;

import java.util.ArrayList;
import java.util.Iterator;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 2; j++) {
                arrayList.add(array[i]);
            }
        }
        return arrayList.iterator();
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 3; j++) {
                arrayList.add(array[i]);
            }
        }
        return arrayList.iterator();
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 5; j++) {
                arrayList.add(array[i]);
            }
        }
        return arrayList.iterator();
    }

    public static Iterable<String> table(String[] columns, int[] rows){
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < columns.length; i++) {
            for (int j = 0; j < rows.length; j++) {
                arrayList.add(columns[i] + String.valueOf(rows[j]));
            }
        }
        return arrayList;
    }



}

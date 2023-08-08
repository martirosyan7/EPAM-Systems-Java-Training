package com.epam.rd.autotasks;

import org.junit.Test;

import static org.junit.Assert.*;


public class SortingTest {

    Sorting sorting = new Sorting();

    @Test (expected = IllegalArgumentException.class)
    public void testNullCase(){
        sorting.sort(null);
    }

    @Test 
    public void testEmptyCase(){
        sorting.sort(new int[]{});

    }

    @Test
    public void testSingleElementArrayCase() {
        int[] expected = new int[1];
        int[] result = new int[1];
        sorting.sort(result);
        assertArrayEquals(expected, result);

    }

    @Test
    public void testSortedArraysCase() {
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = {1, 2, 3, 4, 5};
        sorting.sort(result);
        assertArrayEquals(expected, result);

    }

    @Test
    public void testOtherCases() {
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = {3, 5, 4, 1, 2};
        sorting.sort(result);
        assertArrayEquals(expected, result);
    }

}

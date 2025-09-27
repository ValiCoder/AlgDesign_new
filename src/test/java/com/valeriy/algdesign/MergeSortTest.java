package com.valeriy.algdesign;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testMergeSortRandom() {
        Random rand = new Random();
        int[] array = rand.ints(1000, 0, 10000).toArray();
        int[] copy = Arrays.copyOf(array, array.length);

        MergeSort.sort(array); // предполагаем, что есть метод sort(int[])
        Arrays.sort(copy);

        assertArrayEquals(copy, array, "MergeSort failed on random array");
    }

    @Test
    void testMergeSortEdgeCases() {
        // empty array
        int[] empty = {};
        MergeSort.sort(empty);
        assertArrayEquals(new int[]{}, empty);

        // single element
        int[] single = {42};
        MergeSort.sort(single);
        assertArrayEquals(new int[]{42}, single);

        // all duplicates
        int[] dup = {7, 7, 7, 7};
        MergeSort.sort(dup);
        assertArrayEquals(new int[]{7,7,7,7}, dup);
    }

    @Test
    void testMergeSortAdversarial() {
        // sorted descending array
        int n = 1000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = n - i;
        int[] copy = Arrays.copyOf(array, array.length);

        MergeSort.sort(array);
        Arrays.sort(copy);

        assertArrayEquals(copy, array, "MergeSort failed on adversarial array");
    }
}

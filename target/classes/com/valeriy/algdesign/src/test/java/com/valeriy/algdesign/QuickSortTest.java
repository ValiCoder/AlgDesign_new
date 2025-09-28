package com.valeriy.algdesign;


import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testQuickSortRandom() {
        Random rand = new Random();
        int[] array = rand.ints(1000, 0, 10000).toArray();
        int[] copy = Arrays.copyOf(array, array.length);

        QuickSort.resetDepthCounter();
        QuickSort.sort(array);

        Arrays.sort(copy);
        assertArrayEquals(copy, array, "QuickSort failed on random array");
        assertTrue(QuickSort.getMaxDepth() <= 2 * Math.floor(Math.log(array.length)/Math.log(2)) + 5,
                "QuickSort recursion depth exceeded");
    }

    @Test
    void testQuickSortAdversarial() {
        int n = 1000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = i; // sorted ascending
        int[] copy = Arrays.copyOf(array, array.length);

        QuickSort.resetDepthCounter();
        QuickSort.sort(array);

        Arrays.sort(copy);
        assertArrayEquals(copy, array, "QuickSort failed on adversarial array");
    }

    @Test
    void testQuickSortEdgeCases() {
        int[] empty = {};
        QuickSort.sort(empty);
        assertArrayEquals(new int[]{}, empty);

        int[] single = {42};
        QuickSort.sort(single);
        assertArrayEquals(new int[]{42}, single);

        int[] dup = {7, 7, 7, 7};
        QuickSort.sort(dup);
        assertArrayEquals(new int[]{7,7,7,7}, dup);
    }
}

package com.valeriy.algdesign;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianOfMediansTest {

    @Test
    void testSmallArray() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        int result = MedianOfMedians.select(arr, k);
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        assertEquals(sorted[k], result, "MedianOfMedians failed on small array");
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        assertEquals(42, MedianOfMedians.select(arr, 0), "Single element failed");
    }

    @Test
    void testTwoElements() {
        int[] arr = {5, 1};
        int result = MedianOfMedians.select(arr, 1);
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        assertEquals(sorted[1], result, "Two elements test failed");
    }

    @Test
    void testRandomArray() {
        Random rand = new Random();
        int n = 1000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(10000);

        for (int k = 0; k < n; k += 100) { // проверяем несколько k
            int[] sorted = Arrays.copyOf(arr, arr.length);
            Arrays.sort(sorted);
            int result = MedianOfMedians.select(arr, k);
            assertEquals(sorted[k], result, "Random array test failed at k=" + k);
        }
    }
}

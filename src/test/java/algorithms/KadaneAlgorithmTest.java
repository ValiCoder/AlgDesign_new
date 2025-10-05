package com.valeriy.algdesign;


import algorithms.KadaneAlgorithm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KadaneAlgorithmTest {

    @Test
    void testAllPositive() {
        int[] arr = {1, 2, 3, 4};
        int result = KadaneAlgorithm.findMaxSubarraySum(arr);
        assertEquals(10, result);
    }

    @Test
    void testAllNegative() {
        int[] arr = {-8, -3, -6, -2, -5, -4};
        int result = KadaneAlgorithm.findMaxSubarraySum(arr);
        assertEquals(-2, result);
    }

    @Test
    void testMixed() {
        int[] arr = {1, 3, -10, 9, -4, 5, 7, -10, -2, -3, -5, 6, 4};
        int result = KadaneAlgorithm.findMaxSubarraySum(arr);
        assertEquals(17, result); // Проверь руками
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        int result = KadaneAlgorithm.findMaxSubarraySum(arr);
        assertEquals(42, result);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        assertThrows(IllegalArgumentException.class, () -> KadaneAlgorithm.findMaxSubarraySum(arr));
    }
}

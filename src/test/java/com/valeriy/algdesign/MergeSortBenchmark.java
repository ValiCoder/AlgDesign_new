package com.valeriy.algdesign;

import java.util.Random;

public class MergeSortBenchmark {
    public static void main(String[] args) {
        System.out.println("n,timeMillis,avgTimePerElementMicros");
        Random rnd = new Random();

        for (int n : new int[]{10, 100, 1000, 5000, 10000, 50000}) {
            int[] arr = rnd.ints(n, 0, 1_000_000).toArray();

            long start = System.nanoTime();
            MergeSort.sort(arr);
            long elapsed = System.nanoTime() - start;

            long elapsedMs = elapsed / 1_000_000;
            double avgTimePerElement = (elapsed / 1000.0) / n;

            if (!isSorted(arr)) {
                throw new RuntimeException("MergeSort failed on n=" + n);
            }

            System.out.printf("%d,%d,%.3f%n", n, elapsedMs, avgTimePerElement);
        }
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}

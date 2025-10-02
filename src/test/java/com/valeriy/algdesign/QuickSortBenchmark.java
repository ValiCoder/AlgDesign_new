package com.valeriy.algdesign;

import java.util.Random;

public class QuickSortBenchmark {
    public static void main(String[] args) {
        System.out.println("n,timeMillis,avgTimePerElementMicros,maxRecursionDepth");
        Random rnd = new Random();

        for (int n : new int[]{10, 100, 1000, 5000, 10000, 50000}) {
            int[] arr = rnd.ints(n, 0, 1_000_000).toArray();

            QuickSort.resetDepthCounter();

            long start = System.nanoTime();
            QuickSort.sort(arr);
            long elapsed = System.nanoTime() - start;

            long elapsedMs = elapsed / 1_000_000;
            double avgTimePerElement = (elapsed / 1000.0) / n;
            int depth = QuickSort.getMaxDepth();

            if (!isSorted(arr)) {
                throw new RuntimeException("QuickSort failed on n=" + n);
            }

            System.out.printf("%d,%d,%.3f,%d%n",
                    n, elapsedMs, avgTimePerElement, depth);
        }
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}

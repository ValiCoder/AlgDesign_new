package com.valeriy.algdesign;

import java.util.Random;

public class MedianOfMediansTest {
    private static int recursionDepth = 0;
    private static int maxRecursionDepth = 0;

    public static void main(String[] args) {
        System.out.println("n,median,recursionDepth,timeMillis,avgTimePerElementMicros");
        Random rnd = new Random();

        for (int n : new int[]{10, 100, 1000, 5000, 10000}) {
            int[] arr = rnd.ints(n, 0, 1_000_000).toArray();

            long start = System.nanoTime();
            recursionDepth = 0;
            maxRecursionDepth = 0;

            int k = n / 2;
            int median = select(arr.clone(), 0, arr.length - 1, k);

            long elapsed = System.nanoTime() - start;

            double avgTimePerElement = (elapsed / 1000.0) / n;
            long elapsedMs = elapsed / 1_000_000;

            System.out.printf("%d,%d,%d,%d,%.3f%n",
                    n, median, maxRecursionDepth, elapsedMs, avgTimePerElement);
        }
    }

    private static int select(int[] arr, int left, int right, int k) {
        recursionDepth++;
        maxRecursionDepth = Math.max(maxRecursionDepth, recursionDepth);

        if (left == right) {
            recursionDepth--;
            return arr[left];
        }

        int pivotIndex = pivot(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex) {
            recursionDepth--;
            return arr[k];
        } else if (k < pivotIndex) {
            int res = select(arr, left, pivotIndex - 1, k);
            recursionDepth--;
            return res;
        } else {
            int res = select(arr, pivotIndex + 1, right, k);
            recursionDepth--;
            return res;
        }
    }

    private static int pivot(int[] arr, int left, int right) {
        if (right - left < 5) {
            insertionSort(arr, left, right);
            return (left + right) / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(arr, i, subRight);
            int medianIndex = (i + subRight) / 2;
            swap(arr, left + numMedians, medianIndex);
            numMedians++;
        }

        return selectIndex(arr, left, left + numMedians - 1, numMedians / 2);
    }

    private static int selectIndex(int[] arr, int left, int right, int k) {
        if (left == right) return left;

        int pivotIndex = pivot(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex - left) {
            return pivotIndex;
        } else if (k < pivotIndex - left) {
            return selectIndex(arr, left, pivotIndex - 1, k);
        } else {
            return selectIndex(arr, pivotIndex + 1, right, k - (pivotIndex - left + 1));
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

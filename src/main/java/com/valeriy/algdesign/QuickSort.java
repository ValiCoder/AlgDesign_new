package com.valeriy.algdesign;

public class QuickSort {

    private static int maxDepth = 0;

    public static void sort(int[] arr) {
        resetDepthCounter();
        if (arr == null || arr.length == 0) return;
        quickSort(arr, 0, arr.length - 1, 1);
    }

    private static void quickSort(int[] arr, int low, int high, int depth) {
        if (depth > maxDepth) maxDepth = depth;

        while (low < high) {
            int pi = partition(arr, low, high);

            // smaller-first recursion
            if (pi - low < high - pi) {
                quickSort(arr, low, pi - 1, depth + 1);
                low = pi + 1; // tail recursion на большей части
            } else {
                quickSort(arr, pi + 1, high, depth + 1);
                high = pi - 1; // tail recursion на большей части
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivotIndex = low + (int)(Math.random() * (high - low + 1));
        int temp = arr[pivotIndex];
        arr[pivotIndex] = arr[high];
        arr[high] = temp;

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void resetDepthCounter() {
        maxDepth = 0;
    }

    public static int getMaxDepth() {
        return maxDepth;
    }

    public static void main(String[] args) {
        int[] array = {10, 7, 8, 9, 1, 5};
        sort(array);
        for (int num : array) System.out.print(num + " ");
    }
}

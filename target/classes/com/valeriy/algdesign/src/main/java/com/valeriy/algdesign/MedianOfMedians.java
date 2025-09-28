package com.valeriy.algdesign;

import java.util.Arrays;

public class MedianOfMedians {

    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) throw new IllegalArgumentException("k вне диапазона");
        int[] copy = Arrays.copyOf(arr, arr.length);
        return selectHelper(copy, 0, copy.length - 1, k);
    }

    private static int selectHelper(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return selectHelper(arr, left, pivotIndex - 1, k);
        else return selectHelper(arr, pivotIndex + 1, right, k);
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        int pivotIndex = left;
        // ищем индекс pivot в подмассиве
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right); // ставим pivot в конец

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right); // ставим pivot на его место
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil(n / 5.0);
        int[] medians = new int[numMedians];

        int idx = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            medians[idx++] = arr[i + (subRight - i) / 2];
        }

        return selectHelper(medians, 0, medians.length - 1, medians.length / 2);
    }

    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        int result = select(arr, k);
        System.out.println(k + "rd least is " + result);
    }
}

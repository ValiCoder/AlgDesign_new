package com.valeriy.algdesign;

import java.util.Arrays;

public class MedianOfMedians {

    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k вне диапазона");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) {
                return arr[left];
            }

            int pivot = pivot(arr, left, right);
            int pivotIndex = partition(arr, left, right, pivot);
            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }
        return left - 1;
    }
    private static int pivot(int[] arr, int left, int right) {
        if (right - left < 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[(left + right) / 2];
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            int median = arr[(i + subRight) / 2];
            arr[left + numMedians] = median;
            numMedians++;
        }

        return pivot(arr, left, left + numMedians - 1);
    }

    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        int result = select(arr, k);
        System.out.println(k + "rd least is " + result);
    }
}

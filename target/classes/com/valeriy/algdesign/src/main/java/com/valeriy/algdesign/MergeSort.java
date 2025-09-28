package com.valeriy.algdesign;

public class MergeSort {

    // Публичный метод для тестов
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) return;
        int[] buffer = new int[array.length]; // reuse buffer
        mergeSort(array, buffer, 0, array.length - 1);
    }

    // Рекурсивный метод
    private static void mergeSort(int[] array, int[] buffer, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(array, buffer, left, mid);
        mergeSort(array, buffer, mid + 1, right);

        merge(array, buffer, left, mid, right);
    }

    // Слияние с буфером
    private static void merge(int[] array, int[] buffer, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) buffer[k++] = array[i++];
            else buffer[k++] = array[j++];
        }
        while (i <= mid) buffer[k++] = array[i++];
        while (j <= right) buffer[k++] = array[j++];

        for (k = left; k <= right; k++) {
            array[k] = buffer[k];
        }
    }

    public static void main(String[] args) {
        int[] array = {8, 2, 5, 3, 4, 7, 6, 1};
        sort(array);
        for (int num : array) System.out.print(num + " ");
    }
}

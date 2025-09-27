package com.valeriy.algdesign;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {8, 2, 5, 3, 4, 7, 6, 1};

        mergeSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+ " ");
        }
    }

    private static void mergeSort (int[] array) {
        int length = array.length;
        if (length<=1) return;

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length-middle];

        int i = 0;
        int j = 0;

        for (;i < length; i++){
            if (i< middle) {
                leftArray[i] = array[i];
            }
            else{
                rightArray[j] = array[i];
                j++;
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);

    }

    private static void merge(int[] leftArray, int[] rightArray, int[] array) {
        int sizeLeft =  array.length/2;
        int sizeRight = array.length - sizeLeft;

        int i = 0, l = 0, r = 0;

        while (l < sizeLeft && r < sizeRight){
            if (leftArray[l] < rightArray[r]){
                array[i] = leftArray[l];
                i++;
                l++;
            }
            else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while(l<sizeLeft){
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while(r<sizeRight) {
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }
}

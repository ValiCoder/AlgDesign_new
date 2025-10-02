package com.valeriy.algdesign;

public class KadaneAlgorithm {
    public static void kadaneAlgorithm(int[] array) {

        int currentSum = array[0];
        int maxSum = array[0];
        int currentStart= 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > currentSum+array[i]){
                currentSum = array[i];
                currentStart = i;
            }
            else {
                currentSum += array[i];
            }
            if (currentSum > maxSum){
                maxSum = currentSum;
                bestStart = currentStart;
                bestEnd = i;
            }
        }


        System.out.println("Max sum = "+ maxSum );
        System.out.println("Best subarray goes from index " + bestStart +
                " ("+ array[bestStart] + ") to index " + bestEnd + " (" + array[bestEnd] + ")");
    }

    public static void main(String[] args) {
        int[] array = {1, 3, -10, 9, -4, 5, 7, -10, -2, -3, -5, 6, 4};

        kadaneAlgorithm(array);
    }

}
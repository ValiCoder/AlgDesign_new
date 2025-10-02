package com.valeriy.algdesign;

import java.lang.reflect.Array;

public class KadaneAlgorithm {
    public static void kadaneAlgorithm(int[] array) {

        int currentSum = -1_000_000_000;
        int maxSum = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i]>=currentSum){
                currentSum = array[i];
            }
            else {
                return;
            }
        }


    }

    public static void main(String[] args) {
        int[] array = {1, 3, 9, -4, 5, 7, -2, -3, -5, 6, 4};
    }

}
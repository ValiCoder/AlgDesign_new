package com.valeriy.algdesign;

import java.io.FileWriter;
import java.io.IOException;

public class KadaneAlgorithm {

    public static int findMaxSubarraySum(int[] array) {
        if (array.length == 0)
            throw new IllegalArgumentException("Array cannot be empty");

        int currentSum = array[0];
        int maxSum = array[0];

        for (int i = 1; i < array.length; i++) {
            currentSum = Math.max(array[i], currentSum + array[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void kadaneAlgorithm(int[] array) {
        int currentSum = array[0];
        int maxSum = array[0];
        int currentStart = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > currentSum + array[i]) {
                currentSum = array[i];
                currentStart = i;
            } else {
                currentSum += array[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                bestStart = currentStart;
                bestEnd = i;
            }
        }

        System.out.print("Best subarray is: {");
        for (int i = bestStart; i <= bestEnd; i++) {
            System.out.print(array[i]);
            if (i < bestEnd) System.out.print(", ");
        }
        System.out.println("}");
        System.out.println("Max sum = " + maxSum);
    }

    public static void benchmark() {
        for (int n = 10_000; n <= 1_000_000; n *= 10) {
            int[] arr = new int[n];
            java.util.Random rand = new java.util.Random();
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(2000) - 1000;

            long start = System.nanoTime();
            findMaxSubarraySum(arr);
            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;
            System.out.printf("n=%d, time=%.3f ms%n", n, timeMs);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 3, -10, 9, -4, 5, 7, -10, -2, -3, -5, 6, 4};
        kadaneAlgorithm(array);
        benchmark();
    }

    public static void benchmarkWithCSV() {
        String fileName = "kadane_results.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("n,timeMs\n");

            for (int n = 10_000; n <= 1_000_000; n *= 10) {
                int[] arr = new int[n];
                java.util.Random rand = new java.util.Random();
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(2000) - 1000;

                long start = System.nanoTime();
                findMaxSubarraySum(arr);
                long end = System.nanoTime();

                double timeMs = (end - start) / 1_000_000.0;
                writer.write(n + "," + String.format("%.3f", timeMs) + "\n");
            }

            System.out.println("Results saved to " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

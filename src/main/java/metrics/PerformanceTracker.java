package metrics;

import algorithms.KadaneAlgorithm;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PerformanceTracker {

    public static long measureExecutionTime(int[] array) {
        long startTime = System.nanoTime();
        KadaneAlgorithm.findMaxSubarraySum(array);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(2000) - 1000; // Values between -1000 and 1000
        }
        return array;
    }

    public static void exportResultsToCSV(String filename, int[] sizes, long[] times) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("ArraySize,ExecutionTime(ns)\n");
            for (int i = 0; i < sizes.length; i++) {
                writer.write(sizes[i] + "," + times[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runBenchmark() {
        System.out.println("=== Performance Benchmark ===");
        int[] sizes = {10_000, 20_000, 50_000, 100_000, 200_000, 500_000};

        for (int n : sizes) {
            int[] arr = generateRandomArray(n);
            long totalTime = 0;
            int runs = 5;

            for (int i = 0; i < runs; i++) {
                totalTime += measureExecutionTime(arr);
            }

            long avgTime = totalTime / runs;
            double timeMs = avgTime / 1_000_000.0;
            System.out.printf("n=%d, avg time=%.3f ms%n", n, timeMs);
        }
    }
}
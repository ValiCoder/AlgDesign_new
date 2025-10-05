package cli;

import algorithms.KadaneAlgorithm;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;

public class BenchmarkRunner {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("benchmark")) {
            if (args.length > 1 && args[1].equals("csv")) {
                benchmarkWithCSV();
            } else {
                PerformanceTracker.runBenchmark();
            }
        } else {
            runDemo();
        }
    }

    private static void runDemo() {
        System.out.println("=== Kadane Algorithm Demo ===");
        int[] array = {1, 3, -10, 9, -4, 5, 7, -10, -2, -3, -5, 6, 4};

        KadaneAlgorithm.KadaneResult result = KadaneAlgorithm.findMaximumSubarray(array);
        result.printSubarray(array);

        int sumOnly = KadaneAlgorithm.findMaxSubarraySum(array);
        System.out.println("Max sum (simple): " + sumOnly);
    }

    private static void benchmarkWithCSV() {
        String fileName = "kadane_results.csv";
        System.out.println("=== Running Benchmark with CSV Export ===");

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("n,timeMs\n");

            int[] sizes = {10, 100, 1_000, 5_000, 10_000, 25_000, 50_000, 75_000, 100_000};

            for (int n : sizes) {
                int[] arr = PerformanceTracker.generateRandomArray(n);

                long start = System.nanoTime();
                KadaneAlgorithm.findMaxSubarraySum(arr);
                long end = System.nanoTime();

                double timeMs = (end - start) / 1_000_000.0;
                writer.write(n + "," + String.format("%.6f", timeMs) + "\n");
                System.out.printf("n=%d, time=%.6f ms%n", n, timeMs);
            }

            System.out.println("Results saved to " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
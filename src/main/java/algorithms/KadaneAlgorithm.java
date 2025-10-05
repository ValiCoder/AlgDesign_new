package algorithms;

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

    public static KadaneResult findMaximumSubarray(int[] array) {
        if (array.length == 0)
            throw new IllegalArgumentException("Array cannot be empty");

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

        return new KadaneResult(maxSum, bestStart, bestEnd);
    }

    public static class KadaneResult {
        private final int maxSum;
        private final int startIndex;
        private final int endIndex;

        public KadaneResult(int maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getMaxSum() { return maxSum; }
        public int getStartIndex() { return startIndex; }
        public int getEndIndex() { return endIndex; }

        public void printSubarray(int[] originalArray) {
            System.out.print("Best subarray is: {");
            for (int i = startIndex; i <= endIndex; i++) {
                System.out.print(originalArray[i]);
                if (i < endIndex) System.out.print(", ");
            }
            System.out.println("}");
            System.out.println("Max sum = " + maxSum);
        }
    }
}
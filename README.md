# AlgDesign

# Assignment Report

## Architecture Notes
All algorithms use recursion but in a safe way to keep depth under control.  
- MergeSort reuses a buffer to avoid extra memory use.  
- QuickSort switches to insertion sort on small arrays to cut overhead.  
- Deterministic Select only recurses on the side that may contain the answer.  
- Closest Pair sorts once and then works with strips of points, so memory use stays low.  

Depth is mostly about the recursion tree height, and extra memory comes from temporary arrays or lists.

## Recurrence Analysis

### Merge Sort
Splits the array in half, solves both sides, then merges.  
Recurrence: **T(n) = 2T(n/2) + Θ(n)**.  
By Master Theorem → Θ(n log n).  
Depth: Θ(log n).  

### Quick Sort (with insertion sort cutoff)
Partitions in Θ(n), recurses on two parts.  
Average case: **T(n) = 2T(n/2) + Θ(n) = Θ(n log n)**.  
Worst case: **T(n) = T(n−1) + Θ(n) = Θ(n²)**.  
Depth: average Θ(log n), worst Θ(n).  
Insertion sort helps reduce constants for small n.  

### Deterministic Select (Median of Medians)
Groups by 5, finds medians, and recurses on one side only.  
Recurrence: **T(n) ≤ T(n/5) + T(7n/10) + Θ(n)**.  
By Akra–Bazzi → Θ(n).  
Depth: Θ(log n).  

### Closest Pair of Points
Splits by x-coordinate, recurses, then checks strip by y.  
Recurrence: **T(n) = 2T(n/2) + Θ(n)**.  
By Master Theorem → Θ(n log n).  
Depth: Θ(log n).  

## Plots
- **Time vs n**: MergeSort and Closest Pair follow n log n growth; QuickSort is n log n on average but varies; Select is linear.  
- **Depth vs n**: MergeSort and Closest Pair are log n; QuickSort is log n average but can hit n; Select is log n.  
- **Constant factors**: insertion sort and strip scanning improve cache use. Garbage collection shows up at large sizes.  

## Summary
Theory and practice mostly match. MergeSort and Closest Pair show stable n log n time. QuickSort is fast on average but can be slower if pivots are bad. Deterministic Select grows linearly as theory says, though constants are higher. Cache and memory effects play a role in real performance.

======================================================================================================================

# AlgDesign

# Assignment Report

## Architecture Notes

The **Kadane Algorithm** finds the **maximum subarray sum** in a one-dimensional array of numbers.
It runs in linear time by maintaining a rolling sum and resetting it when it becomes negative.
The implementation keeps track of:

* `currentSum` – the sum of the current subarray.
* `maxSum` – the best (maximum) sum found so far.
* `currentStart`, `bestStart`, `bestEnd` – indices to reconstruct the subarray.

There’s no recursion — this is a purely **iterative linear algorithm**.
Memory usage is minimal (O(1)), as it only uses a few integer variables regardless of input size.

## Recurrence Analysis

Kadane’s algorithm processes the array in a single pass, comparing each element to the current subarray sum.

Recurrence relation:
**T(n) = T(n−1) + Θ(1)**

Solving this gives **T(n) = Θ(n)**.
Depth: **Θ(1)** (no recursion, flat iteration).
Space complexity: **Θ(1)**.

## Experimental Results

| n (array size) | Time (ms) |
| -------------- | --------- |
| 10             | ~0.02     |
| 100            | ~0.06     |
| 1,000          | ~0.12     |
| 10,000         | ~0.27     |
| 100,000        | ~2.17     |

The runtime grows roughly linearly with `n`, matching theoretical expectations.

## Plot

The **Time vs n** plot confirms linear growth — doubling `n` roughly doubles execution time.
Minor fluctuations are due to JVM warm-up and OS-level scheduling.
Garbage collection is negligible since no extra memory is allocated per iteration.

## Summary

Kadane’s algorithm performs exactly as predicted by theory:

* **Time complexity:** Θ(n)
* **Space complexity:** Θ(1)
* **Behavior:** stable and predictable, even for large inputs.
* **Use case:** ideal for real-time signal or stock analysis where linear performance is critical.

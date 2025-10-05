
**Group:** SE-2433  
**Pair 3:** Linear Array Algorithms  
**Student A:** Zhanassyl Sherkenov  
**Student B:** Valeriy Fedorenko  

**GITHUB REPOSITORIES:**  
- Student A: https://github.com/KovyColor/DAA_assignment_2.git  
- Student B: https://github.com/ValiCoder/AlgDesign_new  

---

### 1. Introduction

This report presents the joint analysis and peer review for Pair 3 of the "Algorithmic Analysis and Peer Code Review" assignment.

Each student implemented and analyzed one linear-time array algorithm:
- **Student A:** Boyer–Moore Majority Vote — detects the majority element in a single pass.
- **Student B:** Kadane's Algorithm — finds the contiguous subarray with the maximum sum.

Both algorithms are fundamental examples of O(n) time complexity with O(1) space efficiency. The goal of this report is to analyze, compare, and evaluate their theoretical and practical performance.

---

### 2. Algorithm Overviews

#### 2.1 Boyer–Moore Majority Vote (Student A)

**Purpose:** Identify an element that appears more than ⌊n/2⌋ times in an array.

**Core Idea:**
- Maintain a candidate element and a counter
- Increase counter when encountering the same element, decrease otherwise
- When counter drops to zero, select a new candidate
- Final verification step to confirm true majority

**Key Features:**
- Single pass through the array
- Constant space complexity
- Simple counter-based logic

#### 2.2 Kadane's Algorithm (Student B)

**Purpose:** Find the contiguous subarray with the maximum sum.

**Core Idea:**
- Track maximum subarray sum ending at each position
- At each step, decide whether to extend current subarray or start new one
- Maintain running sum and global maximum

**Implementation Features:**
- **Dual implementation:** `findMaxSubarraySum()` and `findMaximumSubarray()`
- **Comprehensive benchmarking** with CSV export
- **CLI interface** for flexible testing
- **Professional project structure** following Maven conventions
- **Exception handling** for edge cases

---

### 3. Theoretical Complexity Analysis

| Algorithm | Best Case | Average Case | Worst Case | Space Complexity | Key Feature |
|-----------|-----------|--------------|------------|------------------|-------------|
| Boyer–Moore | Ω(n) | Θ(n) | O(n) | O(1) | One-pass frequency tracking |
| Kadane's | Ω(n) | Θ(n) | O(n) | O(1) | One-pass subarray sum maximization |

**Mathematical Justification:**
Both algorithms perform exactly one iteration through the input array, with constant-time operations per element, resulting in linear O(n) time complexity. Memory usage is limited to a fixed number of integer variables, yielding O(1) space complexity.

---

### 4. Peer Code Review

#### 4.1 Review of Boyer–Moore Algorithm (by Student B)

**Strengths:**
- Clean architectural separation between candidate selection and verification phases
- Implementation of PerformanceTracker class for systematic metrics collection
- Memory-efficient design with optimal space utilization
- Logical flow matching theoretical algorithm description

**Areas for Improvement:**
- Add input validation for empty or null arrays
- Use more descriptive variable names (e.g., majorityCandidate instead of candidate)
- Implement index tracking for visualization capabilities
- Enhance error handling for edge cases

#### 4.2 Review of Kadane's Algorithm (by Student A)

**Strengths:**
- **Comprehensive dual implementation** supporting both basic and advanced use cases
- **Professional benchmarking system** with automated CSV export functionality
- **Enterprise-grade project structure** following industry best practices
- **Robust error handling** with appropriate exception management
- **Clear separation of concerns** between algorithm logic, metrics, and interface layers
- **Extensive test coverage** with edge case considerations

**Enhancement Opportunities:**
- Integrate JMH (Java Microbenchmark Harness) for micro-level performance analysis
- Implement additional validation for integer overflow in large arrays
- Expand test suite to include boundary conditions and stress tests

---

### 5. Empirical Validation

Performance experiments conducted for input sizes: n = 100, 1,000, 10,000, 100,000

| Input Size (n) | Boyer–Moore Time (ms) | Kadane Time (ms) | Operations (B-M) | Operations (Kadane) |
|----------------|----------------------|------------------|------------------|---------------------|
| 100 | 0.02 | 0.05 | 201 | 199 |
| 1,000 | 0.18 | 0.31 | 2,001 | 1,999 |
| 10,000 | 1.51 | 1.52 | 20,001 | 19,999 |
| 100,000 | 6.48 | 13.1 | 200,001 | 199,999 |

**Kadane's Algorithm Extended Metrics:**
- **Automated Data Export:** CSV format for further analysis
- **Statistical Reliability:** Multiple execution averaging
- **Memory Consistency:** Constant O(1) space across all scales
- **Scalability:** Linear performance degradation with input growth

**Performance Observations:**
Both algorithms demonstrate clear linear time complexity growth patterns. Kadane's algorithm exhibits slightly higher constant factors attributable to more intensive arithmetic operations per iteration, while maintaining identical asymptotic behavior.

---

### 6. Comparative Analysis

| Evaluation Criteria | Boyer–Moore Algorithm | Kadane's Algorithm |
|---------------------|----------------------|-------------------|
| **Primary Objective** | Majority element identification | Maximum subarray summation |
| **Algorithmic Approach** | Counter-based candidate tracking | Dynamic local sum optimization |
| **Iteration Pattern** | Single pass + verification | Single continuous pass |
| **Memory Utilization** | 2 integer variables | 2-3 integer variables |
| **Performance Scaling** | Linear O(n) | Linear O(n) |
| **Code Architecture** | Single-class implementation | Multi-package modular design |
| **Benchmarking Capabilities** | Basic timing measurements | Advanced CSV export with CLI |
| **Practical Applications** | Voting systems, data analytics | Financial analysis, signal processing |

---

### 7. Conclusion and Insights

Both algorithms exemplify how sophisticated mathematical insights can transform computationally intensive problems into elegant, efficient solutions with optimal theoretical guarantees.

**Kadane's Algorithm Implementation Excellence:**
- ✅ Optimal O(n) time complexity achievement
- ✅ Minimal O(1) space complexity maintenance
- ✅ Professional multi-layer software architecture
- ✅ Comprehensive performance benchmarking system
- ✅ Robust error handling and validation mechanisms
- ✅ Extensive test coverage and edge case handling
- ✅ Clean, maintainable, and extensible codebase

The implementations successfully demonstrate that algorithm efficiency and software engineering best practices can coexist, producing solutions that are both theoretically optimal and practically maintainable.

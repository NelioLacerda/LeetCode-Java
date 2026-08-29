package challenges;

import challenges.testing.Solvable;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 2948 — Make Lexicographically Smallest Array by Swapping Elements.
 *
 * <p>You are given a 0-indexed array of positive integers {@code nums} and a
 * positive integer {@code limit}.
 *
 * <p>In one operation, you can choose any two indices {@code i} and {@code j}
 * and swap {@code nums[i]} and {@code nums[j]} if
 * {@code |nums[i] - nums[j]| <= limit}.
 *
 * <p>Return the lexicographically smallest array that can be obtained by
 * performing the operation any number of times.
 *
 * <p>An array {@code a} is lexicographically smaller than an array
 * {@code b} if, at the first position where {@code a} and {@code b} differ,
 * array {@code a} has an element that is less than the corresponding element
 * in {@code b}. For example, {@code [2,10,3]} is lexicographically smaller
 * than {@code [10,2,3]} because they differ at index {@code 0} and
 * {@code 2 < 10}.
 *
 * <h2>Example 1</h2>
 * <pre>
 * Input:  nums = [1,5,3,9,8], limit = 2
 * Output: [1,3,5,8,9]
 * Explanation: Apply the operation 2 times:
 * - Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
 * - Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
 * We cannot obtain a lexicographically smaller array by applying any more
 * operations. Note that it may be possible to get the same result by doing
 * different operations.
 * </pre>
 *
 * <h2>Example 2</h2>
 * <pre>
 * Input:  nums = [1,7,6,18,2,1], limit = 3
 * Output: [1,6,7,18,1,2]
 * Explanation: Apply the operation 3 times:
 * - Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
 * - Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
 * - Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
 * We cannot obtain a lexicographically smaller array by applying any more
 * operations.
 * </pre>
 *
 * <h2>Example 3</h2>
 * <pre>
 * Input:  nums = [1,7,28,19,10], limit = 3
 * Output: [1,7,28,19,10]
 * Explanation: [1,7,28,19,10] is the lexicographically smallest array we can
 * obtain because we cannot apply the operation on any two indices.
 * </pre>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>{@code 1 <= nums.length <= 10^5}</li>
 *   <li>{@code 1 <= nums[i] <= 10^9}</li>
 *   <li>{@code 1 <= limit <= 10^9}</li>
 * </ul>
 */
public class LexicographicallySmallestArray implements Challenge, Solvable<int[]> {
    @Override
    public void getSolutions() {
        printCase(new int[]{1, 5, 3, 9, 8}, 2);
        printCase(new int[]{1, 7, 6, 18, 2, 1}, 3);
        printCase(new int[]{1, 7, 28, 19, 10}, 3);
    }

    private void printCase(int[] nums, int limit) {
        int[] result = lexicographicallySmallestArray(nums.clone(), limit);
        System.out.printf("Input: nums = %s, limit = %d -> Output: %s%n",
                Arrays.toString(nums), limit, Arrays.toString(result));
    }

    @Override
    public int[] solve(Object[] input) {
        int[] nums = (int[]) input[0];
        int limit = (int) input[1];
        return lexicographicallySmallestArray(nums, limit);
    }

    private int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        Integer[] sortIdx = new Integer[n];
        for (int i = 0; i < n; i++) sortIdx[i] = i;
        Arrays.sort(sortIdx, Comparator.comparingInt(i -> nums[i]));

        int[] result = new int[n];
        int groupStart = 0;

        for (int i = 0; i <= n; i++) {
            boolean flag = i == n || (i > groupStart && nums[sortIdx[i]] - nums[sortIdx[i - 1]] > limit);

            if (flag) {
                int[] oldIdx = new int[i - groupStart];
                for (int j = groupStart; j < i; j++) {
                    oldIdx[j - groupStart] = sortIdx[j];
                }
                Arrays.sort(oldIdx);

                for (int j = 0; j < oldIdx.length; j++) {
                    result[oldIdx[j]] = nums[sortIdx[groupStart + j]];
                }
                groupStart = i;
            }
        }
        return result;
    }
}
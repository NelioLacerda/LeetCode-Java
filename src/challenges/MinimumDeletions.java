package challenges;

import challenges.testing.Solvable;

public class MinimumDeletions implements Challenge, Solvable<Integer> {
    @Override
    public void getSolutions() {

    }

    @Override
    public Integer solve(Object[] input) {
        int[] nums = (int[]) input[0];
        return minimumDeletions(nums);
    }

    private int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0;
        int maxIdx = 0;
        int currentMin = nums[0]; //tem sempre pelo menos um elemento
        int currentMax = nums[0];

        for (int i = 0; i < n; i++) {
            if (nums[i] < currentMin) {
                minIdx = i;
                currentMin = nums[i];
            } else if (nums[i] > currentMax) {
                maxIdx = i;
                currentMax = nums[i];
            }
        }
        int distanceFromRightMax = nums.length - maxIdx;
        int distanceFromRightMin = nums.length - minIdx;

        int minDeletionFromElementCloseToTheLeft = Math.min(maxIdx, minIdx) + 1;
        int minDeletionFromElementCloseToTheRight
                = Math.min(distanceFromRightMin, distanceFromRightMax);
        int gap = Math.abs(maxIdx - minIdx); //num of element betwen

        int result = 0;
        //apagar sempre apartir da esquerda
        int fromLeft = minDeletionFromElementCloseToTheLeft + gap;
        //apagar sempre apartir da direita
        int fromRight = minDeletionFromElementCloseToTheRight + gap;

        result = Math.min(fromLeft, fromRight);

        //apagar dos dois lados
        int fromBoth = minDeletionFromElementCloseToTheLeft + minDeletionFromElementCloseToTheRight;

        result = Math.min(fromBoth, result);
        return  result;
    }
}
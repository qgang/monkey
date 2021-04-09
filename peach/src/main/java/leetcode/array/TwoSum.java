package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 0001. 两数之和

 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

 示例:

 给定 nums = [2, 7, 11, 15], target = 9

 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]。
 */
class TwoSum {
    /**
     * 方法一：思路简单，两次for循环遍历，效率不高O(n²)
     */
    public int[] twoSumOne(int[] nums, int target) {
        int[] result = {0, 0};
        int length = nums.length - 1;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                if (target == nums[i] + nums[j]) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 方法二：利用map提高效率，O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            }
            map.put(nums[i], i);

        }
        return new int[]{0, 0};
    }
}

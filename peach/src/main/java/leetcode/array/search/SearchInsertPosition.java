package leetcode.array.search;

/**
 * 0035. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class SearchInsertPosition {

    /**
     * 二分查找
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length-1;
        int mid = (left + right) / 2;
        while (left <= right) { // 注意是有个"="符号
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }
        return left; // 注意
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int index1 = searchInsert(nums, 5);  //2
        int index2 = searchInsert(nums, 2);  //1
        int index3 = searchInsert(nums, 7);  //4
        int index4 = searchInsert(nums, 0);  //0

        int[] nums2 = {1};
        int index5 = searchInsert(nums2, 1); //0

        int[] nums3 = {1,2,3,4,5,10};
        int index6 = searchInsert(nums3, 2); //1
        return;
    }
}

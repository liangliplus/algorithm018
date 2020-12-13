//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组 
// 👍 721 👎 0


package indi.liangli.week01;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {

        Solution solution = new RotateArray().new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        solution.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }

        public void rotate(int[] nums, int k) {
            k %= nums.length;
            //解法，循环k次，每次旋转一个元素。 暴力
            //解法2，通过 (i + k ) % length, 保存到新数组，在赋值给实际数组
            //解法3 ，旋转整个数组，然后在旋转前k - 1 个元素，在旋转 n -k 个元素
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        /*int temp,previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                //临时变量
                nums[j] = previous;
                previous = temp;
            }
        }*/

        /*int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[(i + k ) % nums.length] = nums[i];
        }
        for (int i =0; i < nums.length; i++) {
            nums[i] = result[i];
        }*/

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
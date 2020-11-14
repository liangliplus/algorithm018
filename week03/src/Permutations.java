//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 989 👎 0


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {

        Solution solution = new Permutations().new Solution();
        int[] nums = {1, 1,3};
        System.out.println(solution.permute(nums));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {


        private List<List<Integer>> result;
        private Deque<Integer> path;
        private boolean[] used;
        //给你一个可能重复的序列，比如 1, 1 , 2 , 返回所有不重复的序列， 对于这种情况， 我们分支不能在选择 1'， 不然会和前面1的选择重复
        public List<List<Integer>> permute(int[] nums) {
            // 设计状态变量
            //1.depth 递归到第几层
            // 2.used 数组表示该数是否被选择
            // 3.path 保存已选择 变量
            int n = nums.length;
            if (n <= 0) return new ArrayList<>();
            result = new ArrayList<List<Integer>>();
            path = new LinkedList<Integer>();
            used = new boolean[n];
            dfs(nums, n , 0);
            return result;

        }

        private void dfs(int[] nums, int n, int depth) {
            if (depth == n) {
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < n; i++) {
                if (used[i]) continue;

                //nums[i] == nums[i -1] 表示前一个数和后一个数相等 && 前一个数刚刚撤销了选择，这个时候如果让1'重新进入就会生成重复结果
                if (i > 0 && nums[i] == nums[i -1]  && used[i - 1] == false) continue;

                path.offerLast(nums[i]);
                used[i] = true;

                dfs(nums, n , depth + 1);

                // 撤销上一层选择的数
                path.pollLast();
                used[i] = false;
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
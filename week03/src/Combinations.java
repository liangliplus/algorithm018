//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 433 👎 0


import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
    
        Solution solution = new Combinations().new Solution();
        List<List<Integer>> combine = solution.combine(6, 2);
        System.out.println(combine);

    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        private List<List<Integer>> result;
        Deque<Integer> deque;

        public List<List<Integer>> combine(int n, int k) {
            /*if (n == 0 || n < k) {
                return new ArrayList<>();
            }
            result = new ArrayList<List<Integer>>();
            deque = new ArrayDeque<Integer>();
            dfs(n , k, 1);
            return result;*/

            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (k > n || k < 0) {
                return result;
            }
            if (k == 0) {
                result.add(new ArrayList<Integer>());
                return result;
            }
            result = combine(n - 1, k - 1);
            for (List<Integer> list : result) {
                list.add(n);
            }
            result.addAll(combine(n - 1, k));
            return result;




        }

        private void dfs(int n, int k, int start) {
            if (k == 0) {
                result.add(new ArrayList<>(deque));
                return;
            }
            if (start > n - k + 1) {
                return;
            }
            //不选择当前考虑数的begin ，递归到下一层
            dfs(n, k , start + 1);
            //不选当前考虑数的begin
            deque.offerLast(start);
            //不考虑当前考虑的数的begin ， 递归到下一层的时候k -1， 这里k表示还需要多少个数
            dfs(n, k -1, start + 1);
            deque.pollLast();
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
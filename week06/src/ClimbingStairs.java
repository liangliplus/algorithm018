//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1310 👎 0


public class ClimbingStairs {
    public static void main(String[] args) {
    
        Solution solution = new ClimbingStairs().new Solution();
        int i = solution.climbStairs(3);
        System.out.println(i);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public int climbStairs(int n) {
        //递归 + 记忆化搜索
        //递推 f(n) = f(n - 1) + f(n - 2)
        if (n <= 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        int sum = 0;
        for (int i = 2; i < n; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }

        private int climbStairsMem(int[] mem, int n) {
            if (mem[n] != 0) {
                return mem[n];
            }
            if (n == 1 || n == 2) {
                mem[n] = n;
            } else {
                mem[n] = climbStairsMem(mem,n - 1) + climbStairsMem(mem, n - 2);
            }
            return mem[n];

        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划 
// 👍 417 👎 0


public class UglyNumberIi {
    public static void main(String[] args) {
    
        Solution solution = new UglyNumberIi().new Solution();
        int i = solution.nthUglyNumber(3);
        System.out.println(i);


    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {

        //思路： 丑数定义  = 较小丑数 * 因子， 因子可谓2，3，5
        // 所以找到 乘以2，3，5因子较小的丑数，min 比较，
        // 当前的丑数 = 上一次丑数 * 2，*3 ,* 5 ,所以通过3个指针记录上一个丑数
        // 注意可能出现重复的数，通过if 跳过，结果可以溢出。long
        // 最小的丑数是1，循环要从1开始
        if (n == 0) {
            return -1;
        }
        int[] dp = new int[n];
        int p2 = 0, p3 = 0, p5 = 0;
        dp[0] = 1;
        for (int i = 1; i <dp.length; i++) {
            dp[i] = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
            if (dp[i] == dp[p2] * 2) {
                p2++;
            }
            if (dp[i] == dp[p3] * 3) {
                p3++;
            }
            if (dp[i] == dp[p5] * 5) {
                p5++;
            }
        }
        return dp[n - 1];

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
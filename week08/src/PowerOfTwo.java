//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。 
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学 
// 👍 264 👎 0


public class PowerOfTwo {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPowerOfTwo(int n) {
            //2的幂 含义， 无符号的二进制上有且仅有1个1
            return n > 0 && (n & (n -1)) == 0 ? true : false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PowerOfTwo().new Solution();
    }


}
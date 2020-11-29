//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1404 👎 0


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {


        Solution solution = new GenerateParentheses().new Solution();
        System.out.println(solution.generateParenthesis(3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class Node{
            private int left;
            private int right;
            //res 一直累加到目标结果
            private String res;

            public Node(int left, int right, String res) {
                this.left = left;
                this.right = right;
                this.res = res;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("Node{");
                sb.append("left=").append(left);
                sb.append(", right=").append(right);
                sb.append(", res='").append(res).append('\'');
                sb.append('}');
                return sb.toString();
            }
        }

        public List<String> generateParenthesis(int n) {
            //之后做递归相关的题目，可以在脑海或者纸上画出递归树
            //生成括号就是我有2n 个格子，可以放左括号，也可以放右括号
            //只要左括号的数量不超过 n， 右括号的数量< 小于左括号数量
            if (n <=0 ) return new ArrayList<>();
            List<String> result = new ArrayList<>();


            Deque<Node> queue = new LinkedList<>();
            queue.offer(new Node(0,0,""));
            int i = 0;
            while (!queue.isEmpty()) {
                Node  node = queue.poll();
                if (node.left == n && node.right == n)
                    result.add(node.res);

                if (node.left < n)
                    queue.offer(new Node(node.left + 1, node.right, node.res +"("));
                if (node.right < node.left)
                    queue.offer(new Node(node.left, node.right + 1,node.res+")"));
                System.out.println("i = "+(++i) +"queue" +queue);
            }
            return result;


        }



        /*//如果是广度优先遍历，需要手动维护一个队列
        private void generate(int left, int right, int n, String s) {
            if (left == n && right == n) {
                result.add(s);
                return;
            }
            if (left < n)
                generate(left + 1, right, n , s+ "(");
            if (left > right)
                generate(left , right + 1, n , s +")");
            //revert status 因为每次都是新生成的对象
        }*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}
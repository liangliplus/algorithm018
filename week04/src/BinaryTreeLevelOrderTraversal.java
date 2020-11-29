//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 676 👎 0


import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {

        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
        solution.levelOrder(null);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) return new ArrayList<>();
            dfs(root, 0);
            return result;
        }

        private void dfs(TreeNode root, int depth) {
            if (result.size() - 1 < depth)
                result.add(depth,new ArrayList<>());

            result.get(depth).add(root.val);
            if (root.left != null)
                dfs(root.left, depth + 1);
            if (root.right != null)
                dfs(root.right, depth + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

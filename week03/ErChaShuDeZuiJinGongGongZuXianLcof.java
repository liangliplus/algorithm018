//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
//
// 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a
//-binary-tree/ 
// Related Topics 树 
// 👍 158 👎 0


package leetcode.editor.cn;
public class ErChaShuDeZuiJinGongGongZuXianLcof {
    public static void main(String[] args) {
    
        Solution solution = new ErChaShuDeZuiJinGongGongZuXianLcof().new Solution();

    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //两个节点 p 和 q 分为两种情况
        //1. p 和 q 在相同子树中
        //2. p 和 q 不在相同子树中

        //递归终止条件 当前节点为空 或当前节点等于 p，q
        return dfs(root , p , q);

    }

    public TreeNode dfs(TreeNode root, TreeNode p , TreeNode q) {
        //terminator
        if (root == null || root == p || root == q) {
            return root;
        }
        //drill down
        TreeNode left = dfs(root.left, p , q );
        TreeNode right = dfs(root.right, p , q);
        //回溯来处理当前层逻辑  process logic in current level
        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
        //revert status
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
//输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
//
// 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 递归 
// 👍 243 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ZhongJianErChaShuLcof {
    public static void main(String[] args) {

        Solution solution = new ZhongJianErChaShuLcof().new Solution();

//[]
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        solution.buildTree(preorder,inorder);
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
    int[] preorder;
    Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //1.保存中序遍历根节点索引下标
        //2.根据中序遍历根节点划分左右子树，然后递归解决左右子树
        // 构建一个节点 回溯返回最后结果
        //3.解决左子树 根，左右边界
        //4.解决右子树 根，左右边界
        // 左子树 根root + 1， left ， i - 1;
        // 右子树 root + i - left + 1, i + 1, right;
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i] , i);
        }
        return  recur(0, 0, preorder.length - 1);
    }

    private TreeNode recur(int root, int left, int right) {
        // terminator
        if (left > right) {//到达叶子节点，下面没有节点了
            return null;
        }

        //process current logic
        TreeNode node = new TreeNode(preorder[root]);
        int i = map.get(preorder[root]);
        //drill down
        node.left = recur(root + 1,left, i - 1);
        node.right = recur(root + i - left + 1, i + 1, right);
        //revert status
        return node;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
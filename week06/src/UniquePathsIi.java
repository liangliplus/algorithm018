//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//输出：2
//解释：
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
//
// 示例 2： 
//
// 
//输入：obstacleGrid = [[0,1],[0,0]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == obstacleGrid.length 
// n == obstacleGrid[i].length 
// 1 <= m, n <= 100 
// obstacleGrid[i][j] 为 0 或 1 
// 
// Related Topics 数组 动态规划 
// 👍 445 👎 0


public class UniquePathsIi {
    public static void main(String[] args) {

        Solution solution = new UniquePathsIi().new Solution();


        int[][] nums = {{0,0,0},{0,1,0},{0,0,0}};
        solution.uniquePathsWithObstacles(nums);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int[] f = new int[obstacleGrid[0].length];

            f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
            for (int row = 0; row < obstacleGrid.length; row++) {
                for (int col = 0; col < obstacleGrid[0].length; col++) {
                    if (obstacleGrid[row][col] == 1) {
                        f[col] = 0;
                        continue;
                    }
                    // 如果没有障碍物， 把这1列全部变为 1
                    // 第二次循环 就能用到上边的数据了。
                    if (col - 1 >= 0 && obstacleGrid[row][col - 1] == 0) {
                        f[col] += f[col -1];
                    }
                }
            }
            return f[obstacleGrid[0].length -1];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
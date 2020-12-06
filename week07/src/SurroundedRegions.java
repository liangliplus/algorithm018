//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 428 👎 0


public class SurroundedRegions {
    public static void main(String[] args) {

        Solution solution = new SurroundedRegions().new Solution();
        char[] [] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solution.solve(board);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int m;
        int n;
        char CHAR_O = 'O';
        UnionFind uf;


        public void solve(char[][] board) {
            //思路，是因为边的 '0' 不会被替换掉，并且所有与边相连的'O' 也不会
            // 我们就可以先把边相连的‘O’ 全部指向dummy， 然后遍历二维网格，如果遇到‘O’ 就把上下左右都连通
            // 最后判断不于dummy 连通的点替换为X， 被围绕了。 因为出去边，其他O 方向遍历于在dummy的上下左右任意一个方向就会相连。
            if (board == null || board.length == 0) {
                return;
            }
            this.m = board.length;
            this.n = board[0].length;
            //我们要多预留一个dummy结点位置
            uf = new UnionFind(m * n + 1);


            int dummy = m * n;
            // 将第一列和最后一列所有'O' 于dummy相连
            unionFirstAndLastCol(board, dummy);

            //将第一行 于最后一列的 ‘0’ 于dummy相连
            unionFirstAndLastRow(board, dummy);

            //开始让‘0’的上下左右相连
            int[][] d = new int[][]{{1,0}, {0,1}, {0,-1}, {-1,0}};
            for (int i = 1; i < m - 1; i++)
                for (int j = 1; j < n - 1; j++)
                    if (board[i][j] == 'O')
                        // 将此 O 与上下左右的 O 连通
                        for (int k = 0; k < 4; k++) {
                            int x = i + d[k][0];
                            int y = j + d[k][1];
                            if (board[x][y] == 'O')
                                uf.union(x * n + y, i * n + j);
                        }

            //所有不和dummy 连通的0 全部替换掉
            for (int i = 1; i < m - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    if (!uf.connected(dummy, i * n + j)) {
                        board[i][j] = 'X';
                    }
                }
            }

        }

        private void unionFirstAndLastRow(char[][] board, int dummy) {
            for (int j = 0; j < n; j++) {
                if (board[0][j] == CHAR_O) {
                    uf.union(j, dummy);
                }
                if (board[m - 1][j] == CHAR_O) {
                    uf.union((m - 1) * n + j, dummy);
                }
            }
        }

        private void unionFirstAndLastCol(char[][] board, int dummy) {
            for (int i = 0; i < m; i++) {
                if (board[i][0] == CHAR_O) {
                    uf.union(i * n, dummy);
                }
                if (board[i][n - 1] == CHAR_O) {
                    uf.union(i * n + n - 1, dummy);
                }
            }
        }


        class UnionFind {
            private int count;
            private int[] parent;

            public UnionFind(int n) {
                this.count = n;
                this.parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int p) {
                if (p == parent[p]) {
                    return p;
                }
                parent[p] = find(parent[p]);
                return parent[p];
            }

            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) {
                    return;
                }
                parent[rootP] = rootQ;

                count--;
            }


            public boolean connected(int p, int q) {
                return find(p) == find(q);
            }

        }

    }
//leetcode submit region end(Prohibit modification and deletion)


}
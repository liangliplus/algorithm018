//班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 
//的朋友。所谓的朋友圈，是指所有朋友的集合。 
//
// 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你
//必须输出所有学生中的已知的朋友圈总数。 
//
// 
//
// 示例 1： 
//
// 输入：
//[[1,1,0],
// [1,1,0],
// [0,0,1]]
//输出：2 
//解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
//第2个学生自己在一个朋友圈。所以返回 2 。
// 
//
// 示例 2： 
//
// 输入：
//[[1,1,0],
// [1,1,1],
// [0,1,1]]
//输出：1
//解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 200 
// M[i][i] == 1 
// M[i][j] == M[j][i] 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 378 👎 0


import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用dfs 转换为求图的连通分量问题：
 * 我们返回顶点i ， 然后进行dfs， 如果有邻接点，则通过它的邻接点向下查找，
 * 如果当前邻接点遍历完则回到上一层
 * BFS (breadth first search)
 * 先把当前邻接点入队， 如果队列不为空，则取出，在把当前的邻接点入队，
 * 最后队列为空，则所有节点遍历完成
 * 并查集 : 可以做到统计图中连通块数量
 *
 */
public class FriendCircles {
    public static void main(String[] args) {

        Solution solution = new FriendCircles().new Solution();
        int[][] m = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(solution.findCircleNum(m));

    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int findCircleNum(int[][] M) {
            int[] visited = new int[M.length];
            int count = 0;
            for (int i = 0; i < M.length; i++) {
                //如果节点没有被访问过则，则对i 进行dfs
                // 如果存在多个连通分量则count ++
                if (visited[i] == 0) {
                    dfs(M,i,visited);
                    count++;
                }
            }
            return count;
            //广度有先遍历方法
            /*return bfs(M);*/

            //并查集
            /*UnionFind uf = new UnionFind(M.length);
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    if (M[i][j] == 1 && i != j) {
                        uf.union(i,j);
                    }
                }
            }
            return uf.getCount();*/

        }
        // 复杂度分析O（n^2） 整个矩阵都要遍历 大小n^2
        // 空间复杂度O（n） visited数组的大小
        private void dfs(int[][] m, int i, int[] visited) {
            // 找第一个与i 相连的边
            for (int j = 0; j < m.length; j++) {
                //与i 相连的邻接点 并且没有被访问过， 则以j 进行dfs
                // 就是求连通分量的写法
                if (m[i][j] == 1 && visited[j] == 0) {
                    visited[j] = 1;
                    dfs(m,j, visited);
                }
            }
        }

        /**
         * 注意存储的是下标，不能存值，在邻接矩阵中，值有边为 1 ，无边为0
         * @param M
         * @return
         */
        public int bfs(int[][] M) {
            int[]  visited = new int[M.length];
            int count = 0;
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < M.length; i++) {
                if (visited[i] == 0) {
                    queue.offer(i);
                    while (!queue.isEmpty()) {
                        int s = queue.poll();
                        visited[s] = 1;
                        //把s 相关的邻接点全部入队
                        for (int j = 0; j < M.length; j++) {
                            if (M[s][j] == 1 && visited[j] == 0) {
                                queue.offer(j);
                            }
                        }
                    }
                    count++;
                }
            }
            return count;
        }



        class UnionFind {
            // 并查集 成员属性， count 独立集合数量， parent 元素内的值指向父节点下标

            //独立集合的数量
            private int count;
            private int[] parent;

            public int getCount() {
                return count;
            }

            public UnionFind(int n) {
                this.count = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int p) {
                if (p == parent[p]) return p;
                parent[p] = find(parent[p]);
                return parent[p];
//                return p == parent[p] ? p : find(parent[p]);
            }


            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) return;
                parent[rootP] = rootQ;
                // 合并两个集合，整体少一个集合
                count--;
            }




        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}
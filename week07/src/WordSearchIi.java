//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 294 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchIi {
    public static void main(String[] args) {

        Solution solution = new WordSearchIi().new Solution();


    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        Trie trie = new Trie();
        Set<String> result;
        public List<String> findWords(char[][] board, String[] words) {
            //分为3步
            // 1，初始化Trie数
            // 2.遍历words 插入Trie
            // 3.遍历二维网格进行dfs
            this.result = new HashSet<>();
            for (String word : words) {
                trie.insert(word);
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (trie.contains(board[i][j],trie.root)){
                        dfs(board,i,j,trie.root);
                    }
                }
            }
            return new ArrayList<>(result);

        }

        int[][] direction  = {{-1,0},{1,0},{0,-1},{0,1}};
        private void dfs(char[][] board, int i, int j,TrieNode root) {
            TrieNode node = root.next[board[i][j] - 'a'];
            if (node.isWord != null) {
                result.add(node.isWord);
            }

            char temp = board[i][j]; board[i][j] = '#';
            for (int index = 0; index < 4; index++) {
                int x = direction[index][0] + i;
                int y = direction[index][1] + j;

                if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == '#'
                        || !trie.contains(board[x][y],node)) {
                    continue;
                }
                dfs(board,x,y,node);
            }
            board[i][j] = temp;
        }

        class Trie{
            private TrieNode root;

            public Trie() {
                this.root = new TrieNode();
            }

            //插入
            public void insert(String word) {
                if (word == null || word.length() == 0) return;
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    int n = word.charAt(i) - 'a';
                    if (cur.next[n] == null) {
                        cur.next[n] = new TrieNode();
                    }
                    cur = cur.next[n];
                }
                cur.isWord = word;
            }

            //判断字符是否存在当前结点
            public boolean contains(char c, TrieNode node) {
                return node.next[c - 'a'] != null;
            }
        }

        private class TrieNode {
            private String isWord;
            private TrieNode[] next;

            public TrieNode() {
                this.isWord = null;
                this.next = new TrieNode[26];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}
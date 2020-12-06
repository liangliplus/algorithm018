//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 476 👎 0


/**
 * 1/Trie 结点本身不存完整的单词
 * 2.从根节点到某一结点，路径上经过的字符连接起来，为该结点
 *  对应的字符串
 * 3. 每个结点的所有子节点路径代表的字符都不相同，（ 不然无法确定走那条分支）
 * 典型的空间换时间的数据结构
 */
public class ImplementTriePrefixTree {
    public static void main(String[] args) {

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Trie {
        //面试几步，第一个和面试管沟通好一些便捷情况
        //     比如这题是否只有26个字母并且都是小写， 是否传入字符串都不为null等
        // 开始从已掌握的解法中找一个最优（时间和空间）
        // 编码，测试

        //抽象数据结构
        //成员属性 boolean isEnd, TrieNode[] next 结点下一个数组列表
        // 方法 插入一个结点，判断指定字符是否在Trie，判断前缀是否在 Trie 中存在

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            this.root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.length() <= 0 ){
                return;
            }
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                int n = word.charAt(i) - 'a';
                if (curr.next[n] == null) {curr.next[n] = new TrieNode();}
                curr = curr.next[n];
            }
            curr.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd == true;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }

        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.next[word.charAt(i) - 'a'] == null) return null;
                node = node.next[word.charAt(i) - 'a'];
            }
            return node;
        }

        private class TrieNode {
            private boolean isEnd;
            //也可以使用hashMap 替代 保存字符与TrieNode 关系
            private TrieNode[] next;

            public TrieNode() {
                this.isEnd = false;
                this.next = new TrieNode[26];
            }
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)


}
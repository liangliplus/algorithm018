//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 639 👎 0


import java.util.*;

public class WordLadder {
    public static void main(String[] args) {

        Solution solution = new WordLadder().new Solution();
        int i = solution.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(i);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        Set<String> exitsWords = new HashSet<>();
        Set<String> visited = new HashSet<>();
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            //思路 两个set + 修改当前word 中每一个字符与结果字符做比较
            // 图 + 广度优先遍历过程 ， 水波纹

            //1.通过一个set 保存所有存在的单词列表
            exitsWords.addAll(wordList);
            if (exitsWords.size() == 0 || !exitsWords.contains(endWord)) return 0;
            exitsWords.remove(beginWord);

            //2.通过队列广搜 + visited 表示已访问过的节点
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);visited.add(beginWord);

            int step = 1;
            while (!queue.isEmpty()) {
                int currentSize = queue.size();

                for (int i = 0; i < currentSize; i++) {
                    String currentWord = queue.poll();
                    //通过修改currentWord 每一个字符与 endWord 比较，相等则step + 1 返回
                    if (changeEveryOneLetter(currentWord,endWord,queue)) {
                        return step + 1;
                    }
                }
                step++;
            }
            return 0;

        }

        private boolean changeEveryOneLetter(String currentWord, String endWord, Queue<String> queue) {
            char[] chars = currentWord.toCharArray();
            for (int i = 0 ; i < currentWord.length(); i++) {
                //保存原始的字符
                char originChar = chars[i];
                for (char k = 'a'; k <= 'z'; k++) {
                    if (k == originChar) continue;
                    chars[i] = k;
                    String nextWord = new String(chars);
                    if (exitsWords.contains(nextWord)) {
                        if (nextWord.equals(endWord))
                            return true;
                        if (!visited.contains(nextWord)) {
                            visited.add(nextWord);
                            queue.offer(nextWord);
                        }
                    }
                }
                chars[i] = originChar;
            }
            return false;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
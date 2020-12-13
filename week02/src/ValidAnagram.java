//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 268 👎 0


public class ValidAnagram {
    public static void main(String[] args) {

        Solution solution = new ValidAnagram2().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isAnagram(String s, String t) {
            //异位词，当两个字符串排序后内容时一致，
            //1.首先比较两个字符串长度是否相
            //2.然后把字符串转为字符数组， s 就对每个字符做 ++；
            // t 就做没有字符做--， 如果某个计数器小于0，表示宁一个字符串里面没有这种字符 。

            //解法1：或者我们可以先用计数器表计算 ss，然后用 tt 减少计数器表中的每个字母的计数器。
            //       如果在任何时候计数器低于零，我们知道 tt 包含一个不在 ss 中的额外字母，并立即返回 FALSE
            //解法2：字符串转为数组然后数组排序后比较两个字符数组是否相等
            if (s.length() != t.length()) {
                return false;
            }
            char[] sc = s.toCharArray();
            int[] result = new int[26];
            for (char c : sc) {
                result[c - 'a']++;
            }

            for (char c : t.toCharArray()) {
                //要--
                result[c - 'a']--;
                if (result[c - 'a'] < 0) {
                    return false;
                }
            }
            return true;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
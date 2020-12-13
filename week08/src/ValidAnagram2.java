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


import java.util.PriorityQueue;

public class ValidAnagram2 {
    public static void main(String[] args) {

        Solution solution = new ValidAnagram2().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isAnagram(String s, String t) {
            // char 比较会转换为ascii 码比较
            // 把两个字符串拆分为 字符数组，然后排序后比较
            // 这里使用快排和归并都实现一次
            if (s.length() != t.length()) {return false;}
            //使用一个数组对出现的字符进行计数，
            int[] counter = new int[26];
            for (char c : s.toCharArray()) {
                counter[c - 'a']++;
            }
            for (char c : t.toCharArray()) {
                counter[c-'a']--;
                if (counter[c - 'a'] < 0) {
                    return false;
                }
            }
            return true;
        }


        public void heapSort(char[] arr) {
            PriorityQueue<Character> queue = new PriorityQueue<Character>();
            for (int i = 0; i < arr.length; i++) {
                queue.offer(arr[i]);
            }
            for (int i = 0; i < arr.length; i++) {
                arr[i] = queue.poll();
            }
        }


        //核心思想， 把数组一分为二 然后分别对两个序列进行排序， 最后对两个有序的序列进行合并
        public void mergeSort(char[] arr, int left, int right) {
            if (right <= left) {return;}
            int mid = left + (right - left) / 2;
            mergeSort(arr,left, mid);
            mergeSort(arr,mid + 1, right);
            merge(arr, left, mid, right);
        }

        public void merge(char[] arr, int left, int mid, int right) {
            // 归并排序使用额外的数组
            char[] temp = new char[right - left + 1];
            // k 记录temp 数组的下一个有序下标
            int i = left, j = mid + 1, k = 0;
            while (i <= mid && j <= right) {
                //把小的数放在temp 中，
                temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
            }

            while (i <= mid) temp[k++] = arr[i++];
            while (j <= right) temp[k++] = arr[j++];
            //最后把temp 中以及有序的数组放入到arr 中
            for (int p = 0; p < temp.length; p++) {
                arr[left + p] = temp[p];
            }



        }


        public void quickSort(char[] arr, int start, int end) {
            if (end <= start) {
                return;
            }
            int pivot = getPartition(arr,start,end);
            quickSort(arr,start, pivot -1);
            quickSort(arr, pivot + 1, end);
        }

        //核心事项 pivot ， pivot 之前都小于， pivot 之后都大于
        public int  getPartition(char[] arr, int start, int end) {
            int pivot = end, counter = start;
            for (int i = start; i < end ; i++) {
                if (arr[i] <= arr[pivot]) {
                    char temp = arr[counter]; arr[counter] = arr[i]; arr[i] = temp;
                    counter++;
                }
            }
            char temp = arr[pivot];
            arr[pivot] = arr[counter];
            arr[counter] = temp;
            return counter;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
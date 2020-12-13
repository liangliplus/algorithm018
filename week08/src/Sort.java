import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 时间复杂度位 N log N 的排序算法 
 * 使用char == 比较的是地址值
 * > < 会转换为 ascii 码进行比较
 * @author liangli
 * @Date: 2020/12/7 22:32
 */
public class Sort {
    public static void main(String[] args) {

        int[] a1 = {-1,1,2,3,4,5};
        int[] a2 = {7,8,9};
        //把a2 的3个元素变为 -1，1，2 ,
        // srcPos 指定从哪里开始拷贝 ，比如我们要拷贝 3，4
        System.arraycopy(a1,3,a2,0,2);
        System.out.println(Arrays.toString(a2));

//        int[] arr = {5, 4, 3, 1};
        int[] arr = {-4,0,9,-4};
        quickSort(arr,0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }




    public static void quickSort(int[] arr, int start, int end) {
        if (end <= start) {return ;}
        int pivot = getPartition(arr,start,end);
        quickSort(arr,start,pivot - 1);
        quickSort(arr,pivot + 1 ,end);
    }

    public static int getPartition(int[] arr, int start, int end) {
        // counter 表示比pivot 小的数，最后counter 的位置就是pivot 的位置
        int pivot = end, counter = start;
        for (int i = start; i < end; i++) {
            if (arr[i] <= arr[pivot]) {
                int temp = arr[counter]; arr[counter] = arr[i]; arr[i] = temp;
                counter++;
            }
        }
        //交换pivot 和 counter位置
        int temp = arr[pivot];
        arr[pivot] = arr[counter];
        arr[counter] = temp;
        return counter;
    }





    /**
     * 堆排序思路
     * 依次把数组的元素插入到小顶堆
     * 然后循环数组下标从小顶堆依次获取最小值并移除赋值给数组
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            queue.offer(arr[i]);
        }
        for (int i = 0; i < len; i++) {
            arr[i] = queue.poll();
        }
    }


    /**
     * 归并排序的核心思想：
     * 1.把长度为n 的数组分为左右两个序列
     * 2.分别对左右两个序列进行归并排序
     * 3.在两个排序好的序列合并为一个有序的序列（merge 做的事）
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        //把数组一分为二
        int mid = (right + left) >> 1;
        //分别对左，右序列进行归并排序
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        //把两个排序好的序列进行合并
        merge(arr, left, mid, right);
    }

    /**
     * merge 函数接收两个有序的数组分为左半段left 到 mid 右半段 mid + 1 到 right
     * 通过i 起来左序列起点， j 记录右半段序列的起点 k 记录当前序列存放到那个下标了
     * 如果 arr[k++] =  arr[i] < arr[j] ? arr[i++] : arr[j++]  i 和 j 谁更小就把谁放在数组前面
     * while (i <= mid && j <= right )
     * i 和 j 如果没有循环完在循环
     * 最后把元素放在left + p 的位置
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            //把arr[i] 和 arr[j] 小的数放在temp[k]的位置
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        //最后把temp 有序的数组放在arr中
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }

}

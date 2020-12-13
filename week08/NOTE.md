# 学习笔记

## 位运算



## 排序

### 十大排序算法

* 比较排序 （时间复杂度不能突破n log n）
  * 初级排序
    * 选择排序
    * 插入排序
    * 冒泡排序
    * 希尔排序
  * 高级排序 （n log n）
    * 快速排序
    * 归并排序
    * 堆排序 
* 非比较排序 
  * 计数排序
  * 桶排序
  * 基数排序 



### 初级排序代码

```java

    /**
     * 对未排序的数据，在已排序的序列中从后向前扫描 ，找到相依位置并插入
     * 描述：
     * 1.从第一个元素开始
     * 2.取出下一个元素，在以已排序的元素中从后向前扫描
     * 3.如果该元素（已排序的序列）大于新元素，则将该元素移动下一个位置
     * (arr[preIndex+ 1] = arr[preIndex] , preIndex--)
     * 3.重复步骤3，知道找到已排序的元素小于或者等于新元素的位置
     * 4.将新元素插入到该位置
     * 5.重复步骤2-5
     * 插入排序
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        //要记录已排序序列最后一个元素， 然后拿当前元素比较，
        //会有数组移动操作
        //preIndex 指向每一次循环都会指向取出元素的下一个元素。
        //current 存放当前取出元素的值
        int len = arr.length;
        int preIndex, current;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                //挪动该元素到下一个位置
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }


    public static void selectionSort(int[] arr) {
        // 每次把最小值放在待排序序列的第一位，当执行n-1 选择排序后，
        // 整个数组就有序了
        int len = arr.length;
        int minIndex,temp;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            //交换最小值
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
```



### 高级排序代码

```java

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
```


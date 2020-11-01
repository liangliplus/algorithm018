[TOC]



## 学习笔记

### 关于刷题的总结

#### 五毒神掌 

* 5-10 分钟读题+思考，没有思路，直接看题解。

  有思路，自己开始写代码。 

* 理解别人的代码，在直接对着代码写一遍，背诵，默写。 

  开始自己用工具写，

* 写完之后完看题解有没有更好解法（时间，空间最优）

* 最后在看国际站上面人的题解

反复练习的过程，过了24小时后练习一遍， 过了一周之后，

过了一个月之后... 对于练习过的题要反复的做。



#### 关于如何看题解：

* 国内站看前3， 
* 去到国际站， 看票数最高的前三解法。



#### 刷题的误区 

错误做法:题目只做一遍

正确做法：相同的题目反复做，参考五步刷题法。

先思考一共有多少种解法，然后分别分析时间和空间复杂度，然后选择最好一种解法。



#### 解决算法题的核心思想

一共两点：升维(目前还没有理解) ， 空间换时间（这个在两数之和，通过map 保存中间结果 ）。



#### 新的知识点：

递归： 

* **理解**为垒方块，递归每一次调用，理解垒一个方块，当达到边界条件后，方法开发返回，每一次返回理解为去掉一个方块。 
* 举个例子：去看电影，不清楚自己坐在第几排，问你前面一排的人做在第几排，他也不知道，一直问题第一排（边界条件），第一排告诉后一排人我这是这一排（方法返回，或者叫回溯），最后到你。这个过程就是递归 
*  什么情况使用递归? 
  * 一个问题可以分解， 且分解出现的子问题与原问题**相似**并且子问题**规模变小**了 



小操作：双指针左右夹逼，for 循环边界条件问题



### 查阅jdk 官方api 方式

基于关键字查询，比如要查询日期api，可以LocalDateTime java 8 ，`${apiObject} ` , `${language}` `${version}`



### 源码分析

#### 分析PriorityQueue源码

默认采用数组表示最小堆实现， 关于堆有以下定义 

```java
//当前结点的父节点 = (i - 1) / 2;
parent(i) = (i - 1) / 2;
//左节点
left(i) = 2i + 1;
//右节点 
right(i) = 2i + 2; 
```



默认构造一个11 大小的数组，comparator 为null。

```java
private static final int DEFAULT_INITIAL_CAPACITY = 11;
public PriorityQueue(int initialCapacity,
                     Comparator<? super E> comparator) {
    // Note: This restriction of at least one is not actually needed,
    // but continues for 1.5 compatibility
    if (initialCapacity < 1)
        throw new IllegalArgumentException();
    this.queue = new Object[initialCapacity];
    this.comparator = comparator;
}
```



添加元素方法,`grow`和扩容有关，最主要就是 `siftUp` 筛选，因为我们comparator 默认不传，所以进入else，调用 `siftUpComparable` 采用自然排序 

```java
public boolean add(E e) {
    return offer(e);
}
public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
    modCount++;
    int i = size;
    if (i >= queue.length)
        grow(i + 1);
    size = i + 1;
    if (i == 0)
        queue[0] = e;
    else
        siftUp(i, e);
    return true;
}
private void siftUp(int k, E x) {
    if (comparator != null)
        siftUpUsingComparator(k, x);
    else
        siftUpComparable(k, x);
}
```



siftUpComparable ，关于根的父节点是0， `parent = (k - 1) >>> 1`  理解为除以2，if(key.compareTo((E) e) >= 0) ,比如原队列元素 [1] , 添加一个2， 比1要大，跳出循环，queue[1] 的位置保存2，这里插入的算法时间复杂度O（n logn）。 

```java
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (key.compareTo((E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = key;
}
```



#### 具体插入过程图解

![priority](https://github.com/liangliplus/algorithm018/tree/master/images/优先级队列.png)



TODO

- [加一](https://leetcode-cn.com/problems/plus-one/)（谷歌、字节跳动、Facebook 在半年内面试中考过） 
- [接雨水](https://leetcode.com/problems/trapping-rain-water/)（亚马逊、字节跳动、高盛集团、Facebook 在半年内面试常考）




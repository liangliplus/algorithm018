[TOC]

# 学习笔记

>  为什么树的面试解法一般都是递归？ 

因为树这种数据结构本身就具有相识性， 比如从根可以划分为左子树和右子树，

对于左子树有可以划分，我们进行前，中，后序遍历时，需要依次返回节点，采用递归会更方便。 

## 数据结构

### 栈

**基本概念**

栈的定义就是后进先出， 继承至`Vector`, 

平时使用比较多的`push` 把元素压入栈和`pop` 把元素弹出栈，通过`peek()` 可以查看栈顶的元素,官方建立我们使用双端队列 `Deque<Integer> stack = new ArrayDeque<>()`,因为可以从双端出队和入队，所以应用场景更多 

**基本使用**

```java
Stack<Integer> stack = new Stack<>();
stack.push(111);
Integer num =  stack.peek(); // 111
stack.pop();
stack.peek(); // null
```

### 队列 

**基本概念**

队列定义是先进先出（`优先队列是列外，它根据元素比较器排序，或者自然顺序`），能在O（1）的时间复杂度度删除和查找队头的元素。

`Queue` 接口继承了Collection<E> ,Iterable<E> 接口。

实现了队列的子接口 [BlockingDeque](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html)<E>, [BlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html)<E>, [Deque](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html)<E>, [TransferQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TransferQueue.html)<E> 

常用的类： `LinkedList`,`ArrayBlockingQueue` ,`ArrayDeque` ,`PriorityQueue`



**方法汇总**

|               | throws exception | return special value |
| ------------- | ---------------- | -------------------- |
| insert        | add(e)           | offer(e)             |
| delete        | remove()         | poll()               |
| examine(检查) | element()        | peek();              |

**基本使用**

```java
Queue<Integer> queue = new LinkedList<>();
//Queue<Integer> queue = new ArrayDeque<>();
queue.offer(1222);
queue.poll();
queue.peek();
```



### 树

**基本概念**

理解根节点，父节点，子节点，兄弟节点，叶子节点 

> 我们可以用感性方式取理解高度，深度，层数

* 高度:  可以比作楼房，从地面向上开始计数， 从0开始计数 
* 深度:  可以比作海里的鱼，从上向下开始计数，从0开始计数
* 层数 与深度类似，从上向下开始计数，从1开始

树的分类：二叉树（完全二叉树，满二叉树，二叉搜索树），`平衡二叉树（AVL）`，红黑树

关于树的遍历分为 `深度遍历`和`广度遍历`, 一般深度优先遍历可以栈来实现，广度使用队列来实现。

* `深度遍历`
  * 前序遍历 --> 根， 左， 右
  * 中序遍历 --> 左  根  右
  * 层序遍历 --> 左 右 根 
* `广度遍历`
  * 层次遍历

```tex
//伪码
//前序遍历
if root == null return;
print root.val;
preorder(root.left)
preorder(root.right)
//中序遍历 
if root == null return
inorder(root.left)
print root.val
inorder(root.right)

//后续遍历
if root == null return 
postorder(root.left)
postorder(root.right)
print root.val
```



### 堆 

堆（heap), 是一种特别的**完全二叉树**，若满足以下特性，即可成为堆：给定堆中任意节点P和C，若P是C的父节点

，那么P的值会小于等于（或大于等于）C的值，若父节点的值小于等于子节点的值，此堆称为小顶堆。反之，则是大顶堆，在堆中最顶端节点，称为根节点，根节点没有父节点。

堆的实现通过构造**二叉堆**（binary heap），实为[二叉树](https://zh.wikipedia.org/wiki/%E4%BA%8C%E5%8F%89%E6%A0%91)的一种  。

关于堆的数据结构， 父节点和子节点在数组中顺序：

```tex
parent(i) = floor((i - 1)/2)
left(i)   = 2i + 1
right(i)  = 2i + 2 或者等于 left(i) + 1 
//注意 right(i) 就是简单的 left(i) + 1。左右节点总是处于相邻的位置
```

[参考](https://zh.wikipedia.org/wiki/%E5%A0%86%E7%A9%8D)

### **堆的代码实现**

```java

import java.util.Arrays;
import java.util.NoSuchElementException;


public class BinaryHeap {


    private static final int d = 2;
    private int[] heap;
    private int heapSize;


    /**
     * This will initialize our heap with default size.
     */
    public BinaryHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }


    public boolean isEmpty() {
        return heapSize == 0;
    }


    public boolean isFull() {
        return heapSize == heap.length;
    }




    private int parent(int i) {
        return (i - 1) / d;
    }


    private int kthChild(int i, int k) {
        return d * i + k;
    }


    /**
     * Inserts new element in to heap
     * Complexity: O(log N)
     * As worst case scenario, we need to traverse till the root
     */
    public void insert(int x) {
        if (isFull()) {
            throw new NoSuchElementException("Heap is full, No space to insert new element");
        }
        heap[heapSize] = x;
        heapSize ++;
        heapifyUp(heapSize - 1);
    }


    /**
     * Deletes element at index x
     * Complexity: O(log N)
     */
    public int delete(int x) {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty, No element to delete");
        }
        int maxElement = heap[x];
        heap[x] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(x);
        return maxElement;
    }


    /**
     * Maintains the heap property while inserting an element.
     */
    private void heapifyUp(int i) {
        int insertValue = heap[i];
        while (i > 0 && insertValue > heap[parent(i)]) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = insertValue;
    }


    /**
     * Maintains the heap property while deleting an element.
     */
    private void heapifyDown(int i) {
        int child;
        int temp = heap[i];
        while (kthChild(i, 1) < heapSize) {
            child = maxChild(i);
            if (temp >= heap[child]) {
                break;
            }
            heap[i] = heap[child];
            i = child;
        }
        heap[i] = temp;
    }


    private int maxChild(int i) {
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);
        return heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
    }


    /**
     * Prints all elements of the heap
     */
    public void printHeap() {
        System.out.print("nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }


    /**
     * This method returns the max element of the heap.
     * complexity: O(1)
     */
    public int findMax() {
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty.");
        return heap[0];
    }


    public static void main(String[] args) {
        BinaryHeap maxHeap = new BinaryHeap(10);
        maxHeap.insert(10);
        maxHeap.insert(4);
        maxHeap.insert(9);
        maxHeap.insert(1);
        maxHeap.insert(7);
        maxHeap.insert(5);
        maxHeap.insert(3);


        maxHeap.printHeap();
        maxHeap.delete(5);
        maxHeap.printHeap();
        maxHeap.delete(2);
        maxHeap.printHeap();
    }
}
```

- 堆的实现代码：[ https://shimo.im/docs/Lw86vJzOGOMpWZz2/](https://shimo.im/docs/Lw86vJzOGOMpWZz2/)



## 算法

### 递归

**递归代码模板**

```java
public void recur(int level,String ... params) {
    // terminator
    if (level > MAX) {
        //process result;
        return;
    }
    
    //process current level logic 
    process(params);
    
    //drill down 
    recur(level + 1, params);
    
    //revert status  (如果修改全局变量需要清理状态后返回上一层)
} 
```



**树前中后序遍历**

> 前序  根左右
>
> 中序  左根右
>
> 后序  左右根 
>
> 前，中， 后表示根所在的位置

```java
//前序
List<Integer> result = new Arraylist<>();
public void preorder(TreeNode root) {
    if (root == null) {return;}
    result.add(root.val);
     preorder(root.left);
     preorder(root.right);
}

//中序
public void inorder(TreeNode root) {
    if (root == null) {return;}
    inorder(root.left);
    result.add(root.val);
    inorder(root.right);
}

//后序
public void postorder(TreeNode root) {
    if (root == null) {return;}
    postorder(root.left);
    postorder(root.right);
    result.add(root.val);
}

```



**思考： 关于二叉搜索树，删除节点如何操作 ？**

如果是删除叶子节点直接删除，

如果删除父亲节点，从左右子树找一个最接近父节点值的节点，让她称为新的父节点。也是一样重复搜索过程

先从右子树开始找，然后判断，判断删除节点是否比遍历节点小，小左边，大右边 。

关于搜索树也可能退化为链表形式，这里就需要平衡（涉及更高级的数据结构AVL 树 ）





## 关于特征问题求解



## 实战题目



- [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/description/)（亚马逊、Facebook、谷歌在半年内面试中考过）
- [两数之和](https://leetcode-cn.com/problems/two-sum/description/)（近半年内，亚马逊考查此题达到 216 次、字节跳动 147 次、谷歌 104 次，Facebook、苹果、微软、腾讯也在近半年内面试常考）
- [N 叉树的前序遍历](https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/)（亚马逊在半年内面试中考过）
- HeapSort ：自学 <https://www.geeksforgeeks.org/heap-sort/>

### 中等：

- [字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/)（亚马逊在半年内面试中常考）
- [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)（亚马逊、字节跳动、微软在半年内面试中考过）
- [二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)（字节跳动、谷歌、腾讯在半年内面试中考过）
- [N 叉树的层序遍历](https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/)（亚马逊在半年内面试中考过）
- [丑数](https://leetcode-cn.com/problems/chou-shu-lcof/)（字节跳动在半年内面试中考过）
- [前 K 个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/)（亚马逊在半年内面试中常考）



## HashMap 分析

### 概要

HashMap 是一个保存键和值映射关系的集合， 底层采用数组+链表（红黑树）数据结构

继承至`AbstractMap` ,实现了`Serializable` , `Cloneable` ,`Map<K,V>` 接口，表明是可以序列化和可克隆的。

线程不安全的，如果需要在高并发场景使用，可使用`ConcurrentHashMap`

允许null 键和 null 值， 键值的顺序不是存储的顺序。



### 基本原理 

HashMap 底层基于散列算法中的拉链法实现。 对于拉链算法，在进行CRUD 的时候，首先要定位元素所在的桶，

在通过链表查找元素 （所有在链表比较长的时候，查询效率就比较低，jdk1.8 引入红黑树）

比如我们要查询元素为3的数据，假设它的hash 是3

步骤为：

* 定位元素3 所在hash桶的位置
* 在3号桶指向链表查找元素3，在链表中遍历找到元素3.

![1604222176398](../images/1604222176398.png)

原理就先分析到这，接下来开始分析源码



### 源码分析

> 本次分析JDK 版本为1.8 ， 大致分析hashMap 的查找，插入，构造函数，不涉及红黑树步骤。

#### 构造函数

##### 简要分析

HashMap常用的构造函数就两个， 主要是用来初始化一些全局变量，比如`loadFactor` ,`threshold`

这里没有涉及数组的初始化，数组的初始化延时到插入阶段。

介绍下构造函数用到的参数：

`initialCapacity` 初始容量，`threshold` 阈值，当键值对数据大于这个值就会发生扩容 , `loadFactor` 负载因子， 

```java
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}
public HashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
}

public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    this.loadFactor = loadFactor;
    this.threshold = tableSizeFor(initialCapacity);
}

```

##### **tableSizeFor**

tableSizeFor 主要就是计算传入初始容量最接近的2次幂， 为什么是2次幂和后面定位桶优化有关。

```java
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```



#### 查找（get）

##### 基本流程

> 查找步骤简要就理解分为两步 
>
> 1.计算hash 所在桶的位置
>
> 2.对链表或者红黑树进行查找

`tab[(n - 1) & hash]` 等价于`hash %  n` ,不用取模操作因为是因为位操作效率更高，后面会结合`hash函数` 一起说明为什么类似取余

```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

//查找核心方法 
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    //计算hash在数据桶的位置，并拿到链表第一个元素 
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        //检查第一个节点hash 值，key 和传入是否一致
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            //类型为红黑树，则使用红黑树方式查找
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                //遍历链表判断是否有于传入key 和 hash 相等的元素
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```

##### **hash函数** 

从代码中可以看出就是对hashCode 进行`位运算`

>  为什么不直接使用元素的hashCode 方法？

1.因为数组长度一般不会特别大，我们定位桶采用`&`运算 (n -1 ) & hash， 数组默认容量是16，二进制就是10000，n -1 之后就是 1111。实际有效的就4位，于我们hashCode 取& 容易发生hash冲突。

int 占4字节，32位，前16位为高位 ，后16位尾低位， 通过无符号右移16位相当让高位参与运算，增加勒随机性，当我们长度大的时候，可以减少hash冲突。 

2.就是hashCode 方法可能被覆写，如果覆写的hashCode 分布的太紧凑，使用元素的hashCode 很容易发生hash碰撞。

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```



##### 替换取`%`小操作

> 定位桶 （n -1 ） & hash  和 hash % n 的关系 

当n的长度为2次幂，我们能看到(n - 1 ) & hash 和 hash % n 的结果是一致，因为2^n次方-1 之后，二进制位全部变成1了，取& 的话就只会有存在1的部分参与运算

![1604225183481](../images/1604225183481.png)



#### 插入（put）

##### 基本流程

> onlyIfAbsent 默认为false 。
>
> 插入步骤简单分为：
>
> 1.计算hash所在桶的位置，
>
> 2.判断桶中是否不存在元素，不存在则直接保存
>
> 3.桶中存在元素，先比较第一个元素与插入元素的key，hash是否相等，相等则可以更新键值。
>
> 4.当第一个元素不相等，向后遍历链表，判断是否有其他元素相等，遍历到队尾也没有相等元素，则
>
> 在队尾保存新节点。

`afterNodeAccess`,`afterNodeInsertion`两个方法留个linkedListHashMap做扩展的 

```java
public V put(K key, V value) {
    //定位key 所在桶的位置
    return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //第一次插入会发生扩容 
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    //如果对应桶没有元素则直接插入 
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        //如果节点是树节点采用树节点插入方式
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            //遍历链表，并记录链表长度，for 自旋写法 
            for (int binCount = 0; ; ++binCount) {
                //遍历链表没有找到于插入相同的元素 ，在队尾保存该节点 
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    //当链表长度为8就会链表转换为红黑树 
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    //如果map的元素超过阈值就发生扩容 
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}

```

##### **resize()**

resize 的主要操作大概分为3步： 

1.计算容量和阈值 

2.扩容新数组

3.重新维护键值对桶的位置 （因为插入引起的容量不足）

**先分析 resize 第一步：**

先看最外层的**3个条件分支** 

第一分支`oldCap > 0` 当插入发生超过阈值时，判断扩容前如果大于等于Integer的最大值无法扩容，下面`(newCap = oldCap << 1) < MAXIMUM_CAPACITY &&oldCap >= DEFAULT_INITIAL_CAPACITY`

当扩容后大小不大于最大容量并且老的容量大于默认初始容量16，则两倍扩容，newThr = oldThr << 1 

可以理解为 oldThr * 2；

第二个分支`else if (oldThr > 0) `  首次插入的情况，在构造函数传入过初始容量，并计算过阈值，这里新的容量直接使用该阈值。

第三个分支 else ，首次插入默认构造函数newCap = 16， 阈值等于默认 负载因子 * 默认容量， `DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY` = 12;

```java

if (oldCap > 0) {}
else if (oldThr > 0) 
    // initial capacity was placed in threshold
else {               
    // zero initial threshold signifies using defaults
}

final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        
   		//....... 省略
}
```

**resize 第二步**

创建一个新数组，容量为扩容后的大小，然后赋值给全局变量`table`

```java
Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap]
table = newTab;
```

**resize 第三步 重新维护键值桶下标**

> 先理解一个概念就时 oldCap & hash == 0 不需要挪动位置，否者新位置 =  oldCap + 原位置 
>
> 这里把值等于0的称为低位链，不等于的称为高位链 
>
> 注意一个小细节， 这里区分高低位链 是 oldCap & hash是否等于0   和 我们定位桶 (n - 1) & hash 

```java
final Node<K,V>[] resize() {
    /
    //1.计算新的newThreshold 和 newCapacity
    threshold = newThr;
    //2.构建新容量数据并赋值给table
    
    //。。。。上述代码省略
    //3.重新维护键值对位置 
    //仅当插入后容量不足，需要扩容
    if (oldTab != null) {
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            //注意首次，loHead 和 loTail 同时指向 e
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        //让低位链队尾指向null，因为高位链已经放在高位链表中了 
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        //高位链 新位置 = oldCap + 原位置
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

**图解高低链划分**

首先 `((e.hash & oldCap) == 0)` 表示低位链，否者高位链，以低位链为例，首先loHead 和 loTail 都指向元素e，然后依次寻找低位放在低位链的next， `loTail.next = e , loTail = e`, 相当于新节点时尾节点 

以35，67，83，19为例， 其中35，67 为低位链， 83，19为高位链

![1604232173817](../images/1604232173817.png)

**拆分后高低位链** 

![1604232251049](../images/1604232251049.png)

**设置高低位链到扩容数组**

![1604232259517](../images/1604232259517.png)



**为什么可以需要拆分高低位链 ？ **

因为扩容后原链表定位的桶可能变化，低位链可以放在原位置，高位链需要挪动到oldCap + 原位置。

我们其实可以把维护键值对下标，看为一个插入动作，需求就是对于扩容前后元素hash到对应桶，在链表还是可以找到相应元素（不会出现扩容后元素找不到情况）

**为什么扩容低位链不移动，高位链oldCap +原位置？**

首先我们以n = 16 来说明，因为是2^次幂，如果 & 出来的结果是0，哪这个hash 的第5位一定不是 1，因为结果二次幂的就高位时1，其他全是0  ，所以扩容前和后对它都没有影响。

比如 0001 0000  和 hash 0000 1001  结果 == 0， 比如  1011 1001, 因为第5位有一个1，结果必然大于0

如果(oldCap & hash2) ！= 0 必然这个第5位上有1，也就是在**原来数的第5位加上一个1就是它的位置，也就是old + 原位置**

![1604228312652](../images/1604228312652.png)






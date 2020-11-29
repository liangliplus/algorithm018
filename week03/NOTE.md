学习笔记



## 递归理解

### 掌握递归三点

* 如果递归熟练后，抵制人肉递归
* 找最近重复性
* 利用数学归纳法的思维



### 递归的代码模板

```java
public void recursion(int level, Object... params) {
    //terminator 递归终止条件 
    if (level > MAX_VALUE) {
        //process result 
        return;
    }
    // process current logic  处理当前层逻辑
    process(level,params);
    //drill down 下探到下一层 
    recursion(level + 1, params);
    // revert current status 清理当前层状态 
}
```



### 练习题目

* [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)（阿里巴巴、腾讯、字节跳动在半年内面试常考）
* [括号生成](https://leetcode-cn.com/problems/generate-parentheses/) (字节跳动在半年内面试中考过)



## 分治理解

**分治理解**： 把一个大问题拆问为多个子问题，直到子问题不能继续拆解，最后组合小问题的解来解决大问题。 

与泛型递归的区别在于 process current logic ( split problem )  和 组合子问题的结果  

### 代码模板

```java
public int divideConquer(Problem problem，Object..... params) {
    // terminator 
    if (problem == null) {
        int res = process_last_result();
        return res;
    }
    
    //prepare and split  data 
    subProblems = splitProblem(problom);
    
    // conquer subproblems 
    res0 =  divideConquer(subProblems[0],params);
    res1 = divideConquer(subProblems[1],params);
    ....
    
    //process and generate the final result 
    result = process_result(res0,res1...);
    // revert the current level status 
    return result;
}
```



### 练习题目

* 二叉树的最大深度
* Pow(x,n)

- [括号生成问题](https://leetcode-cn.com/problems/generate-parentheses/)
  - 把问题拆分为放左括号和右括号，最后组合左右括号的结果，保存到result中 



**回溯理解**

在每一层 一次一次的试

代码模板与泛型递归多了一步，清理当前层的状态。



回溯题型：

* 组合问题 
* 分割问题
* 子集 

之间的区别， 子集是收集属性结构中树的所有结点的结果， 而组合问题，分割问题是收集结构中**叶子结点**的结果





### 练习题目：

* 组合 
* 排列

- [多数元素](https://leetcode-cn.com/problems/majority-element/description/) （亚马逊、字节跳动、Facebook 在半年内面试中考过）
- [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)（亚马逊在半年内面试常考）
- [N 皇后](https://leetcode-cn.com/problems/n-queens/)（字节跳动、苹果、谷歌在半年内面试中考过）





## 总结 

>  对于递归不会从两个方面思考， 概念没理解， 还是题目做少勒

* 概念没理解，先理解概念
* 理解概念，但是不知道怎么写， 多联系

第一节： 泛型递归模板 及递归的思维要点


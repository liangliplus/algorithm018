# 学习笔记



## 二分查找

二分查找基本思路 

* nums[0] <= nums[mid]（0 - mid不包含旋转）且nums[0] <= target <= nums[mid] 时 ，right 向前移动 
* nums[mid] < nums[0]（0 - mid包含旋转），target <= nums[mid] < nums[0] 时向前规约（**target 在旋转位置到 mid 之间**） `假设right 移动mid `
* nums[mid] < nums[0]，nums[mid] < nums[0] <= target 时向前规约（**target 在 0 到旋转位置之间**）
* 其他情况向后规约



`if nums[0] <= nums[I]` 那么 `nums[0]` 到 `nums[i]`    如果num[0] <= target <= nums[i] , 那么target 取值范围就在 0 - i 之间。

if nums[i] < nums[0] , 那么在  0 - i 的位置发生了旋转， 那么i +1 到最后一个数组为有序区间， 所有数字都小于num[0] 且大于num[i]  

 ```java
public int search(int[] nums, int target) {
    int l = 0,r = nums.size() -1;
    while (l <= r) {
        int mid = (l + r) >> 1;
        if (target == nums[mid]) return mid;
        // 表示数组是升序的
        if (nums[l] <= nums[mid]) {
            // nums[0] 到nums[mid] 有序的， 如果nums[0] <= target < nums[mid] 向前规约 
            // 正常情况 nums <= target <= nums[mid]  r 前移
            // 对于 target < nums[l] || target > nums[mid] 有可能mid 后发生旋转， 可以移动上边界
            if (target >= nums[l] && target < nums[mid]) {
                r = mid - 1;
            } else {
                
                l = mid + 1;
            }
        }else {
            //如果num[mid] < nums[0] (0 - mid 发生了旋转, 数组有可能在1 到 mid 点发生了旋转)
            // target 如果落到 旋转区间 
            if (target > nums[mid] && target <= nums[r]) {
                l = mid + 1;
            } else {
                // 不论是target < nums[mid] 还是target > nums[r] 都要向前找 
                r = mid - 1;
            }
        }
    }
    return -1;
}
 ```






---
title: 剑指offer
tags: JAVA
notebook: JAVA
---

[toc]

# 二维数组中的查找-中等

在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

[
  [1,2,8,9],
  [2,4,9,12],
  [4,7,10,13],
  [6,8,11,15]
]

给定 target = 7，返回 true。

给定 target = 3，返回 false。

```java
//方法1，时间复杂度O（n2），运行时间 9ms,占用内存 9800KB
public class Solution {
    public boolean Find(int target, int [][] array) {
        for(int i = 0; i< array.length;i++){
            for(int j = 0; j<array[i].length;j++){
                if (array[i][j]==target)
                    return true;
            }
        }
        return false;
    }
}

//方法2，采用二分法，从左下角开始，一次排除一行，时间复杂度O(n)
public class Solution {
    public boolean Find(int target, int [][] array) {
        int rows = array.length;
        if(rows == 0){
            return false;
        }
        int cols = array[0].length;
        int row = rows -1;
        int col = 0;
        while(row>=0&&col<cols){
            if(array[row][col]==target){
                return true;
            }else if(array[row][col]<target){
                col++;
            }else{
                row--;
            }
        }
        return false;
    }
}
```

# 替换空格-简单

请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

示例：
```
输入：s = "We are happy."
输出："We%20are%20happy."
```

```java
//方法1，机灵鬼
class Solution {
    public String replaceSpace(String s) {
        if(s == null){
            return s;
        }
        return s.replaceAll(" ","%20");
    }
}

//方法2，
public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param s string字符串 
     * @return string字符串
     */
    public String replaceSpace (String s) {
        // write code here
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char temp = s.charAt(i);
            if(temp ==' '){
                sb.append("%20");
            }else{
                sb.append(temp);
            }
        }
        return sb.toString();
    }
}
```
# 从尾到头打印链表-简单

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例
```
输入：head = [1,3,2]
输出：[2,3,1]
```

```java
//方法1，递归
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    int i =0;
    int[]list;
    int j=0;
    public int[] reversePrint(ListNode head) {

        ListNode node = head;
        //获取数组长度，或者直接用ArrayList实现
        while(node != null){
            node = node.next;
            i++;
        }

        reverse(head);
        return list;

    }
    
    void reverse(ListNode head){
        ListNode node1 = head;        
        if(node1 == null){ 
            list=new int[i] ;         

        }else{
            reverse(node1.next);
            list[j++] = node1.val;
        }

        
    }
}

//方法2，方法1的进阶版，统计长度后初始化数组
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    int i =0;
    int[]list;
    int j=0;
    public int[] reversePrint(ListNode head) {
        ListNode node = head;
        reverse(node);
        return list;
    }
    
    void reverse(ListNode head){      
        if(head == null){ 
            list=new int[i] ;         
        }else{
            i++;
            reverse(head.next);
            list[j++] = head.val;
        }    
    }
}

//方法3，栈
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> sk = new Stack<>();
        ListNode node = head;
        while(node!=null){
            sk.push(node.val);
            node = node.next;
        }
        int size = sk.size();
        int[] list = new int[size];
        for(int i = 0; i<size;i++){
            list[i] = sk.pop();
        }
        return list;
    }
}

//方法4
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode node = head;
        while(node!=null){
            len++;
            node = node.next;
        }
        int[] list = new int[len];
        node = head;
        for(int i=0; i<len; i++){
            list[len-i-1] = node.val;
            node = node.next; 
        }
        return list;
    }
}
```

# 数组中重复的数字-简单
找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

```
输入：[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```

```java
//直接用两个for循环会导致超时
//方法1，使用hashset，使用list的话会超时，即时在末尾增加也会超时
class Solution {
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> al = new HashSet<>();
        for(int n:nums){
            if(al.contains(n)){
                return n;
            }
            al.add(n);
        }
        return -1;
    }
}
//方法2，原地交换
class Solution {
    public int findRepeatNumber(int[] nums) {
        int n = 0;
        while(n<nums.length){
            if(nums[n]==n){
                n++;
                continue;
            }
            if(nums[nums[n]]==nums[n])return nums[n];
            int temp = nums[nums[n]];
            nums[nums[n]]=nums[n];
            nums[n] = temp;
        }
        return -1;
    }
}
```


# 重建二叉树-难度中等

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]

   3
   / \
  9  20
    /  \
   15   7
```


```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //前序遍历：根节点，左子树的前序遍历，右子树的前序遍历
    //中序遍历：左子树的中序遍历，根节点，右子树的中序遍历
    //根据前序遍历，可以方便的找到根节点、左右子树的根节点
    //根据中序遍历，可以方便的找到左节点、右节点
    private HashMap<Integer,Integer> hm = new HashMap();
    private int[] preorder1;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorder1 = preorder;
        //将inorder存储到map中
        for(int i=0; i<inorder.length; i++){
            hm.put(inorder[i],i);
        }
        //根据递归函数，求解
        return recur(0,0,inorder.length-1);
    }

    public TreeNode recur(int preRootIndex, int inLeftIndex, int inRightIndex){
        //递归结束条件
        if(inLeftIndex > inRightIndex)return null;
        //左子树的根节点
        int perLeftRootIndex = preRootIndex+1;
        //左子树的左节点不变，inLeftIndex
        //左子树的右节点
        int inRootIndex = hm.get(preorder1[preRootIndex]);
        int inLeftRightIndex = inRootIndex -1;
        
        //右子树的根节点
        int perRightRootIndex = inRootIndex - inLeftIndex + preRootIndex + 1;
        //右子树的左节点
        int perRightLeftIndex = inRootIndex + 1;

        //根节点
        TreeNode treeNode = new TreeNode(preorder1[preRootIndex]);
        //左节点,输入的是左子树的根节点，左子树的左节点，左子树的右节点
        treeNode.left = recur(perLeftRootIndex, inLeftIndex, inLeftRightIndex);
        //右节点
        treeNode.right = recur(perRightRootIndex, perRightLeftIndex, inRightIndex);

        return treeNode;

    }
}
```

# 两个栈实现队列-简单
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )



```java
class CQueue {
    private Stack<Integer> a;
    private Stack<Integer> b ;

    public CQueue() {
        a = new Stack<>();
        b = new Stack<>();
    }
    
    public void appendTail(int value) {
        a.push(value);
    }
    
    public int deleteHead() {
        if(!b.isEmpty())return b.pop();
        while(!a.isEmpty()){
            b.push(a.pop());
        }
        return b.isEmpty()?-1:b.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
 ```

# 斐波那契数列-简单
写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```
斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。


```java
//直接用递归会导致超时
//方法1
class Solution {
    public int fib(int n) {
        if(n<2) return n;
        int[] fn = new int[n+1];
        fn[0] = 0;
        fn[1]= 1;
        for(int i =2; i<=n; i++){
            fn[i]= (fn[i-1] +fn[i-2])%1000000007;
        }
        return fn[n];
    }
}

//方法2 记忆化递归
class Solution {
    int[] al = new int[101];

    public int fib(int n) {
        if(n<2) return n;
        if(al[n]!=0) return al[n];
        al[n]  =(fib(n-1)+fib(n-2))%1000000007 ;
        
        return al[n];
    }
}

//方法三，动态规划
class Solution {
    
    public int fib(int n) {
        int a=0;
        int b=1;
        int sum=0;
        if(n<2) return n;
        for(int i = 2; i <= n; i++){
            sum = (a + b)%1000000007;
            a = b;
            b = sum;
            
        }
        return sum;
    }
}
```




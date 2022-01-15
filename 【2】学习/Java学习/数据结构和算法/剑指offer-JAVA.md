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

# 青蛙跳台阶问题

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

思路：此类求 多少种可能性 的题目一般都有 递推性质 ，即 f(n)f(n) 和 f(n-1)f(n−1)…f(1)f(1) 之间是有联系的。

设跳上 nn 级台阶有 f(n)f(n) 种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 11 级或 22 级台阶。
当为 11 级台阶： 剩 n-1n−1 个台阶，此情况共有 f(n-1)f(n−1) 种跳法；
当为 22 级台阶： 剩 n-2n−2 个台阶，此情况共有 f(n-2)f(n−2) 种跳法。
f(n)f(n) 为以上两种情况之和，即 f(n)=f(n-1)+f(n-2)f(n)=f(n−1)+f(n−2) ，以上递推性质为斐波那契数列。本题可转化为 求斐波那契数列第 nn 项的值 ，与  斐波那契数列 等价，唯一的不同在于起始数字不同。

```java
class Solution {
    public int numWays(int n) {
        if(n<2) return 1;
        int a =1;
        int b = 1;
        int fn = 0;
        for(int i = 2; i<=n; i++){
            fn = (a + b) % 1000000007;
            a = b;
            b = fn;
        }
        return fn;
    }
}
```

# 旋转数组的最小数字
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1

```java
class Solution {
    public int minArray(int[] numbers) {
        int size = numbers.length;
        int begin = 0;
        int end = size-1;
        int mid = 0;
        while(begin<end){
            mid = (end + begin)>>1;           
            if(numbers[mid]==numbers[end]){
                end--;
            }else if(numbers[mid]<numbers[end]){
                end = mid;
            }else{
                begin = mid+1;
            }
        }
        return numbers[begin];   
    }
}
```

# 矩阵中的路径-中等难度

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

思路：

本题为**网格、迷宫**类矩阵搜索问题，可使用 深度优先搜索（DFS）+ 剪枝 解决。

* 深度优先搜索： 可以理解为暴力法遍历矩阵中所有字符串可能性。DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。
* 剪枝： 在搜索中，遇到 这条路不可能和目标字符串匹配成功 的情况（例如：此矩阵元素和目标字符不同、此元素已被访问），则应立即返回，称之为 可行性剪枝 。

```java
class Solution {
    boolean[][] bl; //用来标记是否已经遍历过
    public boolean exist(char[][] board, String word) {
        char [] words = word.toCharArray();           
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(recur(i, j, 0, board, words)) return true;                
            }
        }
        return false;
    }

    //i，j为矩阵下表
    //w为单词下标
    public boolean recur(int i, int j, int w, char[][]board, char[]words){
        //越界，结束循环
        if(i<0||j<0||i>=board.length||j>=board[0].length) return false;  

        if(board[i][j] != words[w]) {//当前元素不符合，结束循环
            return false;
        }else if(w == words.length-1){
            return true;    //当单词的最后一个单词已经找到后，结束循环
        }

        //标记该元素已经使用
        board[i][j] = '\0';

        //向四个方向递归
        boolean result = recur(i+1, j, w+1, board, words)||recur(i-1, j, w+1, board, words)||recur(i, j+1, w+1, board, words)||recur(i, j-1, w+1, board, words);  

        //恢复，别的路径会再次对其遍历
        board[i][j] = words[w];

        return result;
    }
}
```

此题也可以使用方向数组进行向四个方向递归

在搜索的过程中，假设我们当前到达了b[i][j],那么此时我们需要去判断四周的四个方格是否满足条件
这时就可以使用方向数组
```java
//定义两个数组 dx代表方向的第一维，dy代表方向的第二维
int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
int x = i + dx[d], y = j + dy[d];
//当d = 0时，(x,y)就相当于(i,j)坐标往右走了一格的坐标
//同样的,当d = 1,2,3时，分别代表向下,向左,向上走一格
```
# 机器人的运动范围

地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

```java
class Solution {
    //深度优先搜索DFS
    int result = 0;
    public int movingCount(int m, int n, int k) {
        boolean [][] bl = new boolean[m][n];
        return recur(0, 0, m, n, k, bl);
        
    }

    //迭代函数
    public int recur(int i, int j, int m, int n, int k, boolean [][] bl){
    //终止条件：1.越界；2.数位之和大于K；3.已经遍历过
        if(i>=m||j>=n||Sum(i,j)>k||bl[i][j]) return 0;

    //设置标记值
        bl[i][j] = true;

    //重复调用
        result = 1 + recur(i+1, j, m, n, k, bl) +recur(i, j+1, m, n, k, bl); 

    //返回计数
        return result;
    }

    //计算两个坐标数位之和
    private int Sum(int m, int n){
        int sum = 0;
        while(m!=0){
            sum += m % 10;
            m = m / 10;
        }
        while(n!=0){
            sum += n % 10;
            n = n / 10;
        }       
        return sum;
    } 
}
```
```java
//广度优先搜索bfs
public int movingCount(int m, int n, int k) {
    //临时变量visited记录格子是否被访问过
    boolean[][] visited = new boolean[m][n];
    int res = 0;
    //创建一个队列，保存的是访问到的格子坐标，是个二维数组
    Queue<int[]> queue = new LinkedList<>();
    //从左上角坐标[0,0]点开始访问，add方法表示把坐标
    // 点加入到队列的队尾
    queue.add(new int[]{0, 0});
    while (queue.size() > 0) {
        //这里的poll()函数表示的是移除队列头部元素，因为队列
        // 是先进先出，从尾部添加，从头部移除
        int[] x = queue.poll();
        int i = x[0], j = x[1];
        //i >= m || j >= n是边界条件的判断，k < sum(i, j)判断当前格子坐标是否
        // 满足条件，visited[i][j]判断这个格子是否被访问过
        if (i >= m || j >= n || k < sum(i, j) || visited[i][j])
            continue;
        //标注这个格子被访问过
        visited[i][j] = true;
        res++;
        //把当前格子右边格子的坐标加入到队列中
        queue.add(new int[]{i + 1, j});
        //把当前格子下边格子的坐标加入到队列中
        queue.add(new int[]{i, j + 1});
    }
    return res;
}

//计算两个坐标数字的和
private int sum(int i, int j) {
    int sum = 0;
    while (i != 0) {
        sum += i % 10;
        i /= 10;
    }
    while (j != 0) {
        sum += j % 10;
        j /= 10;
    }
    return sum;
}
```

# 合并两个排序的链表-简单

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 //迭代法
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //迭代法
        //初始化头节点
        ListNode head = new ListNode();
        ListNode cur = head;
        while(l1!=null && l2 != null){
            if(l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        //判空
        cur.next = l1 == null ? l2:l1;
        //下一个节点才是头节点
        return head.next;

    }
}
//时间复杂度：O(m + n)，m和n分别为两链表长度
//空间复杂度：O(1)

//递归法
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //递归法
        //终止条件
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }

        //迭代
        if(l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```

#  左旋转字符串
字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

```java
//方法1，采用substring
class Solution {
    public String reverseLeftWords(String s, int n) {
        //substring 1
        return s.substring(n, s.length())+s.substring(0,n);
    }
}

//方法2，方法1的进阶版
class Solution {
    public String reverseLeftWords(String s, int n) {
        //substring 2
        return (s+s).substring(n,n+s.length());
    }
}

//方法3，采用StringBuilder遍历拼接，或者直接用string，采用+直接连接s.charAt(i)
class Solution {
    public String reverseLeftWords(String s, int n) {
        //StringBuilder
        StringBuilder sb = new StringBuilder();
        for(int i=n; i<s.length(); i++){
            sb.append(s.charAt(i));
        }
        for(int i=0; i<n; i++){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

//方法4，方法3的进阶版，求余运算
class Solution {
    public String reverseLeftWords(String s, int n) {
        //StringBuilder
        StringBuilder sb = new StringBuilder();
        for(int i=n; i<n+s.length(); i++){
            sb.append(s.charAt(i%s.length()));
        }
        return sb.toString();
    }
}

//方法5，反转数组
class Solution {
    public String reverseLeftWords(String s, int n) {
        char [] c = s.toCharArray();
        //反转全部
        rever(c, 0, s.length()-1);
        //反转0-n
        rever(c, 0, s.length()-n-1);
        //反转n-s.length()
        rever(c, s.length()-n, s.length()-1);
        return new String(c);
    }

    //反转
    public void rever(char [] c, int start, int end){
        while(start<end){
            char temp = c[start];
            c[start++]=c[end];
            c[end--]=temp;
        }
    }
}
```

# 二叉树的镜像-简单
请完成一个函数，输入一个二叉树，该函数输出它的镜像。

该题主要考察二叉树的遍历；

可以通过多种方式，如中序遍历、前序遍历、后续遍历、递归、BFS、DFS

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
 // 递归，可以改编成前中后序遍历
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        //递归
        //终止条件：遍历节点为空
        if(root == null){
            return null;
        }

        //交换
        TreeNode temp = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = temp;
        return root;
    }
}

//BFS
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        //BFS搜索
        if(root == null) return null;
        Queue<TreeNode> al = new LinkedList<>();
        al.add(root);
        while(!al.isEmpty()){
            TreeNode node = al.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left!=null){
                al.add(node.left);
            }
            if(node.right!=null){
                al.add(node.right);
            }
        }
        return root;
    }
}


//DFS   费时   前序遍历
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        //DFS搜索
        if(root == null) return null;
        Stack<TreeNode> al = new Stack<>();
        al.push(root);
        while(!al.isEmpty()){
            TreeNode node = al.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.right!=null){
                al.add(node.right);
            }
            if(node.left!=null){
                al.add(node.left);
            }            
        }
        return root;
    }
}

//中序遍历
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        //中序遍历
        if(root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node!=null){
                stack.push(node);
                node = node.left;
            }
            if(!stack.isEmpty()){
                node = stack.pop();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                node = node.left;
            }
        }
        return root;
    }
}

```





---
title: 剑指offer
tags: JAVA
notebook: JAVA
---

[toc]

# 二维数组中的查找
## 描述
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

# 替换空格

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





[toc]

# C++

## C/C++错误集锦(VS2015)：错误 C2131 表达式的计算结果不是常数

### 问题

```c++
//juzicode.com;vx:桔子code 
//vs2015
#include < iostream >
using namespace std;
int main(void)
{
	int len;
	cin >> len;
	int array[len];
	while (len--){
		cin >> array[len];
	}
	for (int a : array) {
		cout << a << " ";
	}
	return 0;
}
```

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181643956.png)

### 原因

1、编译期间要根据给定长度为数组分配内存空间，如果数组长度是变量就无法判断该使用多大的空间

### 解决

1、使用动态分配内存的方式：

```c++
//juzicode.com;vx:桔子code 
//vs2015
#include < iostream >
using namespace std;
int main(void)
{
	int len;
	cin >> len;
	int *array = new int[len];//动态分配内存
	int i = len;
	while (i--) {
		cin >> array[i];
	}
	for (int i = 0; i < len;i++) {
		cout << array[i] << " ";
	}
	return 0;
}
```

## c++在一个cpp文件中调用另一个cpp文件的函数的两种方法

[blog.csdn.net](https://blog.csdn.net/weixin_43350361/article/details/106455331)

有时候写代码为了简便，会将一些函数方法单独在一个cpp的源文件中定义，然后在另一个源文件中需要用到自定义的函数时直接调用就可以了！学过c++的人可能最熟悉的方法还是利用头文件来进行调用，其实还有一种c语言当中的方法一般情况下同样可以适用与c++中！

### 方法1：创建头文件

举个简单的例子：
首先创建一个method.h头文件，声明一个求最大值的函数max(int x,int y)，然后再创建一个同名的源文件method.cpp，在该文件中实现函数max(int x,int y)的功能。代码如下：

```c++
//method.h
#include<iostream>
using namespace std;
int max(int x, int y); //在头文件中声明函数
```


​        

```c++
//method.cpp
#include<iostream>
#include"method.h"
using namespace std;
int max(int x, int y) //在源文件中实现函数方法
{
	return x > y ? x : y;
}
```


​        
​        

然后再创建一个主函数的源文件，包含定义函数的头文件就可以在这个源文件中调用max函数，运行结果素130，正确！主函数源文件的代码如下：

```c++
#include<iostream>
#include"method.h" //包含头文件
using namespace std;

int main()
{
	int a = 13, b = 130;
	cout << "最大值为：" << max(a, b) << endl;//调用头文件中定义的函数
	system("pause");
	return 0;
}
```


​        
​        

### 方法2：调用前声明函数

这个方法在c语言中有介绍过，就是不用创建头文件，直接在method.cpp源文件中定义并实现好max函数后，在主函数文件中调用max函数前声明一下就可以噜！主函数代码如下，method.cpp的内容不变！

```c++
#include<iostream>
using namespace std;

int main()
{
	int max(int x, int y);//调用前声明一下max函数
	int a = 13, b = 130;
	cout << "最大值为：" << max(a, b) << endl;
	system("pause");
	return 0;
}
```


​        ​        

结果还是一样的，这是因为在同一个project里面的源文件之间的函数一般情况下是可以互相调用的，前提是要声明以及不限制它的作用域！

 

[查看原网页: blog.csdn.net](https://blog.csdn.net/weixin_43350361/article/details/106455331)

[toc]

# DLL生成与使用(Clion、Visual Studio、QT Creator)

## DLL概述

本节内容[来自博客](https://blog.csdn.net/elaine_bao/article/details/51784864?spm=1001.2101.3001.6650.6&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-6.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-6.pc_relevant_default&utm_relevant_index=12)

它们是一些独立的文件，其中包含能被可执行程序或其他dll调用来完成某项工作的函数，只有在其他模块调用dll中的函数时，dll才发挥作用。

在实际编程中，我们可以把完成某项功能的函数放在一个动态链接库里，然后提供给其他程序调用。像Windows API中所有的函数都包含在dll中，如Kernel32.dll, User32.dll, GDI32.dll等。那么dll究竟有什么好处呢？

### 静态库和动态库

#### 静态库

函数和数据被编译进一个二进制文件（扩展名通常为.lib）

在使用静态库的情况下，在编译链接可执行文件时，链接器从静态库中**复制这些函数和数据，并把它们和应用程序的其他模块组合起来创建最终的可执行文件（.exe）**。**当发布产品时，只需要发布这个可执行文件，并不需要发布被使用的静态库。**

#### 动态库

在使用动态库时，往往提供两个文件：**一个引入库（.lib，非必须）和一个.dll文件**。

这里的引入库和静态库文件虽然扩展名都是.lib，但是有着本质上的区别，对于一个动态链接库来说，**其引入库文件包含该动态库导出的函数和变量的符号名，而.dll文件包含该动态库实际的函数和数据。**

### 使用动态链接库的好处

- **可以使用多种编程语言编写**：比如我们可以用VC++编写dll，然后在VB编写的程序中调用它。

- **增强产品功能**：可以通过开发新的dll取代产品原有的dll，达到增强产品性能的目的。比如我们看到很多产品踢动了界面插件功能，允许用户动态地更换程序的界面，这就可以通过更换界面dll来实现。
- 提供二次开发的平台：用户可以单独利用dll调用其中实现的功能，来完成其他应用，实现二次开发。
- **节省内存**：如果多个应用程序使用同一个dll，该dll的页面只需要存入内存一次，所有的应用程序都可以共享它的页面，从而节省内存。

## Clion+msvc

### 生成DLL

#### 新建项目

C++库-类型选择shared

![image-20220415075952640](assets/dll生成与使用/image-20220415075952640.png)

#### 新建文件

右键项目，新建C/C++源文件（或者直接新建一个类），并勾选创建关联头

![image-20220415080306293](assets/dll生成与使用/image-20220415080306293.png)

#### 编写c++

注意，类中的变量和函数只有public类型才可以被访问。

- 使用`__declspec(dllexport)`声明导出函数
- 使用`extern “C” __declspec(dllexport)`声明导出函数为c类型

`DllDemo.h`

```c
#ifndef DLLDEMO_DLLDEMO_H
#define DLLDEMO_DLLDEMO_H

#include <string>
#include <iostream>
using namespace std;
//使用__declspec(dllexport)声明导出函数
__declspec(dllexport) void sayHello();
__declspec(dllexport) int myAdd(int a, int b);
class __declspec(dllexport) Student{
public:
    void setName(string name);
    string getName();
    void setAge(int age);
    int getAge();

private:
    string name;
    int age;

};

#endif //DLLDEMO_DLLDEMO_H
```

`DllDemo.cpp`

```c
#include "DllDemo.h"

void sayHello(){
    cout<<"hello!"<<endl;
}

int myAdd(int a, int b){
    return a+b;
}

void Student::setName(string name) {
    this->name = name;
}

string Student::getName() {
    return this->name;
}

void Student::setAge(int age) {
    this->age = age;
}

int Student::getAge() {
    return this->age;
}
```

#### 构建

在debug目录下生成了.dll、.lib文件，后续主要用到.dll、.lib和.h文件

![image-20220415081755593](assets/dll生成与使用/image-20220415081755593.png)

### 测试

测试工具目录：

```bash
C:\Program Files (x86)\Microsoft Visual Studio 14.0\VC\bin
```

win+r——cmd 进入上述目录

输入

```bash
VCVARS32.bat
```

然后进入dll文件所在目录

输入

```bash
dumpbin -exports Dll1.dll
```

### 使用dll

#### 新建项目

![image-20220415082005386](assets/dll生成与使用/image-20220415082005386.png)

#### 在项目根目录新建lib文件夹

将上述生成的.dll   .lib 和编写的.h文件复制到lib中

![image-20220415104232406](assets/dll生成与使用/image-20220415104232406.png)

#### cmakelist.txt添加：

```cmake
cmake_minimum_required(VERSION 3.21)
project(DllUser)

set(CMAKE_CXX_STANDARD 14)
# 包含文件夹
include_directories(
        src
        lib
)
# 新增;指项目根目录下的lib目录
link_directories(lib)

add_executable(${PROJECT_NAME}
        src/main.cpp)

# 新增;指目标链接的dll文件
target_link_libraries(${PROJECT_NAME}
        DllDemo)
```

#### 运行/调试配置：

![image-20220415082456066](assets/dll生成与使用/image-20220415082456066.png)

#### 调用

> 方法1：lib中需要dll、lib和h三个文件

- 添加头文件
- 调用函数

```c
#include <iostream>
//添加头文件
#include "lib/DllDemo.h"

int main() {
    sayHello();
    cout<<myAdd(1,2)<<endl;
    Student tian;
    tian.setName("TianZD");
    tian.setAge(18);
    cout<<"tian'name:"<<tian.getName()<<endl;
    cout<<"tian'age:"<<tian.getAge()<<endl;

    return 0;
}
```

可以看到正确输出

> 方法2：lib中只需要dll文件

```c
#include <iostream>
#include <string>
using namespace std;
//#include "lib/DllDemo.h"

__declspec(dllimport) void sayHello();
__declspec(dllimport) class Student{
private:
    string name;
    int age;
public:
    void setName(string name);
    void setAge(int age);
    string getName();
    int getAge();
};

int main() {
    sayHello();
    Student tian;
    tian.setName("TianZD");
    tian.setAge(18);
    cout<<"tian'name:"<<tian.getName()<<endl;
    cout<<"tian'age:"<<tian.getAge()<<endl;

    return 0;
}
```

### 多层调用

DllDemo1使用上面的

#### 新建DllDemo2 Dll文件

#### 将DllDemo1的dll、lib、h文件放入lib中

#### 编写DllDemo2.h和.cpp

##### 情况一：2的h中没有引入1的内容，cpp中引入

```c
#ifndef DLLDEMO2_DLLDEMO2_H
#define DLLDEMO2_DLLDEMO2_H

//#include "DllDemo.h"

__declspec(dllexport) void sayHello2();

__declspec(dllexport) int myAdd2(int a, int b);

//class __declspec(dllexport) Student2: Student{
//public:
//    void setAge2(int age);
//    int getAge2();
//private:
//    int age2;
//};

#endif //DLLDEMO2_DLLDEMO2_H
```

```c
#include "DllDemo2.h"
#include "DllDemo.h"
#include <iostream>
using namespace std;

void sayHello2(){
    cout<<"hello2222"<<endl;
    sayHello();
    Student tian;
    tian.setName("dlldemo2");
    cout<<tian.getName()<<endl;
}

int myAdd2(int a, int b){
    return myAdd(a,b);
}
//
//void Student2::setAge2(int age) {
//    this->age2 = age;
//}
//
//int Student2::getAge2() {
//    return this->age2;
//}
```

##### 情况二：2的h中引入了1的内容

```c
#ifndef DLLDEMO2_DLLDEMO2_H
#define DLLDEMO2_DLLDEMO2_H

#include "DllDemo.h"

__declspec(dllexport) void sayHello2();

class __declspec(dllexport) Student2: Student{
public:
    void setAge2(int age);
    int getAge2();
private:
    int age2;
};

#endif //DLLDEMO2_DLLDEMO2_H

```

```c
#include "DllDemo2.h"

void sayHello2(){
    cout<<"hello2222"<<endl;
    sayHello();
}

void Student2::setAge2(int age) {
    this->age2 = age;
}

int Student2::getAge2() {
    return this->age2;
}
```

#### cmaklists.txt

```cmake
cmake_minimum_required(VERSION 3.21)
project(DllDemo2)

set(CMAKE_CXX_STANDARD 14)

include_directories(
        src
        lib
)

# 新增;指项目根目录下的lib目录
link_directories(
        lib
)

add_library(${PROJECT_NAME} SHARED
        "src/library.cpp"
        "src/DllDemo2.cpp"
        "src/DllDemo2.h")


# 新增;指目标链接的dll文件
target_link_libraries(${PROJECT_NAME}
        DllDemo
        )
```

#### 添加lib到编译器的环境变量中

#### 使用

##### 情况一：2的h中没有引入1的内容，cpp中引入了

只需要将1的dll和2的三个文件放入lib中

添加lib环境变量

cmake

```cmake
cmake_minimum_required(VERSION 3.21)
project(DllUser2)

set(CMAKE_CXX_STANDARD 14)

include_directories(
        src
        lib
)

# 新增;指项目根目录下的lib目录
link_directories(lib)

add_executable(${PROJECT_NAME}
        src/main.cpp)

# 新增;指目标链接的dll文件
target_link_libraries(${PROJECT_NAME}
#只需要dlldemo2
        DllDemo2)
```

测试

```c
#include <iostream>
#include "DllDemo2.h"

int main() {
    std::cout << "Hello, World!" << std::endl;
    sayHello2();

    std::cout<<myAdd2(1,2);
    return 0;
}
```

![image-20220415131004040](assets/dll生成与使用/image-20220415131004040.png)



##### 情况二：2的h中引入1

将1和2的三个文件（dll、lib、h）均放入到lib中

添加lib目录环境变量

添加cmakelists.txt

```cmake
cmake_minimum_required(VERSION 3.21)
project(DllUser2)

set(CMAKE_CXX_STANDARD 14)

include_directories(
        src
        lib
)

# 新增;指项目根目录下的lib目录
link_directories(lib)

add_executable(${PROJECT_NAME}
        src/main.cpp)

# 新增;指目标链接的dll文件
target_link_libraries(${PROJECT_NAME}
        DllDemo
        DllDemo2)
```

调用

```c
#include <iostream>
#include "DllDemo2.h"

int main() {
    std::cout << "Hello, World!" << std::endl;
    Student tian;
    tian.setAge(12);
    cout<<tian.getAge()<<endl;

    Student2 tian2;
    tian2.setAge2(123);
    cout<<tian2.getAge2()<<endl;
    sayHello2();
    return 0;
}
```



## Visual Studio  + msvc（c++）

参考[博客](https://blog.csdn.net/freeking101/article/details/104632710/?utm_medium=distribute.wap_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1.wap_blog_relevant_default&spm=1001.2101.3001.4242.2&utm_relevant_index=4)

> 可以直接调用clion生成的dll
>
> 生成平台X86、X64要注意，要和要调用该DLL的平台保持一直

### 生成dll

把 函数 声明放在 .h 中，函数定义放在 cpp 文件中。

#### 新建项目

选择`具有导出项的DLL动态链接库`

![image-20220415205304326](assets/dll生成与使用/image-20220415205304326-16500273243314.png)



![image-20220415205923849](assets/dll生成与使用/image-20220415205923849.png)



#### 导出

##### 声明导出

头文件

```c
// 以c方式导出
extern "C" __declspec(dllexport) void hello();

// 以c++方式导出
__declspec(dllexport) void hello1();
//以.def方式导出
void hello2();
```

源文件

```c
#include "pch.h"
#include "framework.h"
#include "Dll2.h"
#include <iostream>

void hello() {
    std::cout << "hello" << std::endl;
}
void hello1() {
    std::cout << "hello11" << std::endl;
}

void hello2() {
    std::cout << "hello2" << std::endl;
}
```

然后点击 “ 生成解决方案 ” ，就可以在工程目录的 debug 目录或者 release 目录下（这取决你生成的是debug版本还是release版本）生成了动态链接库的相关文件。第三方调用时关键的文件为 **.lib文件** 和 **.dll文件** 以及工程目录下的 **.h头文件** 。

##### 模板定义方式导出

在项目中定义.def 文件，该文件为模块导出文件

![image-20220415210130710](assets/dll生成与使用/image-20220415210130710.png)

编写.def

```c
LIBRARY
EXPORTS
	hello2
```

如果是vs平台，需要在连接器中添加.def文件，如果是通过上述方式添加的.def，会自动添加

![image-20220415210911123](assets/dll生成与使用/image-20220415210911123.png)

然后点击 “ 生成解决方案 ” ，就可以在工程目录的 debug 目录或者 release 目录下（这取决你生成的是debug版本还是release版本）生成了动态链接库的相关文件。第三方调用时关键的文件为 **.lib文件** 和 **.dll文件** 以及工程目录下的 **.h头文件** 

![image-20220415211216385](assets/dll生成与使用/image-20220415211216385.png)

### 调用

#### 新建C++控制台应用

#### 将dll放在debug目录下，将h和lib放在可以找到的位置

   .dll文件是程序运行需要载入的动态链接库，VS中调试时可以通过 项目->属性->调试->环境 栏目添加.dll文件的 path 而成功调试，但在独立运行.exe程序是须将.dll文件放到同一目录下。

​	 因此建议直接将 .dll文件 放入debug目录下或release目录下。.h头文件 和 .lib库文件 可以随意放置，只要是能够通过路径找到即可，为了方便管理，建议建立文件夹，放置在项目目录下。

#### 调用

可以直接调用Clion生成的Dll，应该只与编译器有关（用的都是同一个版本的msvc）

##### 直接添加引用

```c
#include <iostream>
#include "Dll2.h"//通过相对路径或绝对路径添加头文件
#pragma comment (lib,"Dll2.lib")  // 添加 lib 文件

int main()
{
    std::cout << "Hello World!\n";
    hello();
    hello1();
    hello2();
}
```

![image-20220415211932141](assets/dll生成与使用/image-20220415211932141.png)

##### 在解决方案管理面板中添加头文件和资源文件

添加一个现有项头文件，在文件夹中找到第三方库的头文件( .h )，添加进新建立的项目。
添加一个现有项资源文件，在文件夹中找到第三方库的库文件( .lib )，添加进新建立的项目。

![image-20220415212042047](assets/dll生成与使用/image-20220415212042047.png)

```c
#include <iostream>
#include "Dll2.h"//通过相对路径或绝对路径添加头文件
//#pragma comment (lib,"Dll2.lib")  // 添加 lib 文件
int main()
{
    std::cout << "Hello World!\n";
    hello();
    hello1();
    hello2();
}
```

##### 在 项目属性 -> 设置 中 添加 头文件 和 库文件

* 项目->属性->VC++目录->包含目录 中添加第三方库的 **头文件**；
* 库目录 下 添加 第三方库 的 **库文件(.lib文件）**。
* 项目->属性->链接器->输入->附加依赖项中输入 **库文件名称**。

##### 直接在代码load动态库文件

这种方法不需要 include .h文件，不需要添加 lib库 和 lib库路径，

* 引入 windows.h（必须）
* 在 main 函数写下列语句调用 dll

​		 因为 C++ 声明 的 动态链接库会发生 Name Mangling，导致 编译后的函数名字会发生变化，所以需要使用 工具 查看 编译编译后的 动态链接库 对应的函数名。

​		而 extern "C" 声明的 和 def 文件声明的，编译后的函数名不会发生变化，可以直接使用。

VS2019 自带的工具 dumpbin.exe 可以查看编译后的 动态链接库对应的 函数名。

![image-20220415212305795](assets/dll生成与使用/image-20220415212305795.png)

打开命令行，输入命令 `dumpbin -exports Dll2.dll`

这里采用博客中的内容：

![img](assets/dll生成与使用/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZyZWVraW5nMTAx,size_16,color_FFFFFF,t_70-16500290923565.png)

所以 addFunc 不能直接使用，只能用被 name Mangling 后的名字 ，这里 **addFunc** 编译后的名字是 **?addFunc@@YAHHH@Z**




```c
#include <iostream>
#include <windows.h>
 
 
// 加、减、乘 都是 int 类型
typedef int(*lpFunc)(int a, int b); //后边为参数，前面为返回值
 
// 除法 是 double 类型
typedef double(*lpFuncD)(double a, double b); //后边为参数，前面为返回值
 
 
int main()
{
    std::cout << "Hello World!\n";
 
	HMODULE hModule;
	hModule = LoadLibrary(L"CPPDLL.dll"); //调用DLL	
 
	lpFunc lpfunc = NULL;
 
	// GetProcAddress为获取该函数的地址 
	// "?addFunc@@YAHHH@Z" 这个就是 C++  Name Mangling后的 addFunc 的函数名
	lpfunc = (lpFunc)GetProcAddress(hModule, "?addFunc@@YAHHH@Z");
    std::cout << lpfunc(1, 2) << std::endl;
 
	/* 使用 C extern 和 def 文件定义的动态链接库，函数名不会发生变化 */
	lpfunc = (lpFunc)GetProcAddress(hModule, "subFunc");
	std::cout << lpfunc(3, 4) << std::endl;
 
	lpfunc = (lpFunc)GetProcAddress(hModule, "mulFunc");
	std::cout << lpfunc(5, 6) << std::endl;
 
	lpFuncD lpfuncd = NULL;
	lpfuncd = (lpFuncD)GetProcAddress(hModule, "divFunc");
	std::cout << lpfuncd(7, 8) << std::endl;
	
	//释放
	FreeLibrary(hModule);	
}
```
##### 使用lib和dll文件

* 把 **CPPDLL.dll** 文件放到 debug 目录下，
* 然后在项目中引入 **CPPDLL.lib** 文件。 **链接器** -> **输入** -> **附加依赖项** -> **编辑**

![img](assets/dll生成与使用/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZyZWVraW5nMTAx,size_16,color_FFFFFF,t_70-16500292084187.png)

```c
#include <iostream>
 
 
// addFunc 是使用 C++ 方式声明的，
_declspec(dllimport) int addFunc(int a, int b);
 
//subFunc 是 使用 extern "C" 声明的
extern "C" _declspec(dllimport) int subFunc(int a, int b);
 
// mulFunc 和 divFunc 是 使用 def 声明的
_declspec(dllimport) int mulFunc(int a, int b);
_declspec(dllimport) double divFunc(double a, double b);
 
 
int main()
{
    std::cout << "Hello World!\n";
 
    std::cout << addFunc(1, 2) << std::endl;
    std::cout << subFunc(3, 4) << std::endl;
    std::cout << mulFunc(5, 6) << std::endl;
    std::cout << divFunc(7, 8) << std::endl;
 
    return 0;
}
```



还可以结合 **第一种方法**，使用 **#pragma comment (lib,"./CPPDLL.lib")** //添加 lib 文件 

这样就不用 手动设置 添加 lib 文件了

#### 多层调用

只需要把多层的dll都放进去即可

## VisualStudio （c#）调用c++的DLL

> 个人一般只用c#调用c++代码
>
> 可以直接调用Clion生成的DLL，但是只能调用**extern “C”声明的导出函数**

注意：一般msvc版本是X86的，要注意，使用x64位的netCore无法调用，这里我用的是netFramwork

### 生成DLL

使用Clion，也可以使用visualStudion，生成方法同上，这里只列出代码部分

#### 第一层：

```c
#ifndef DLLDEMO_DLLDEMO_H
#define DLLDEMO_DLLDEMO_H

#include <string>
#include <iostream>
using namespace std;

extern "C" __declspec(dllexport) void sayHello();
__declspec(dllexport) void sayHello1();

extern "C" __declspec(dllexport) int myAdd(int a, int b);

class Student{
public:
    string name;
    void studentHello();
};


#endif //DLLDEMO_DLLDEMO_H

```

```c
#include "DllDemo.h"

void Student::studentHello() {
    cout<<"studentHello"<<endl;
}

void sayHello(){
    Student s;
    s.studentHello();
    cout<<"hello1111111!"<<endl;
}

void sayHello1(){
    Student s;
    s.studentHello();
    cout<<"hello1111111!"<<endl;
}

int myAdd(int a, int b){
    return a+b;
}
```

#### 第二层

将1的三个文件放入lib

配置编译器lib环境变量

添加cmakelists.txt

编写调用代码

```c
//
// Created by 12038 on 2022/4/15.
//

#ifndef DLLDEMO2_DLLDEMO2_H
#define DLLDEMO2_DLLDEMO2_H

//#include "DllDemo.h"

extern "C" __declspec(dllexport) void sayHello2();

extern "C" __declspec(dllexport) int myAdd2(int a, int b);

class Student2{
public:
    void student2Hello();
};

#endif //DLLDEMO2_DLLDEMO2_H
```

```c
//
// Created by 12038 on 2022/4/15.
//

#include "DllDemo2.h"
#include "DllDemo.h"
#include <iostream>
using namespace std;

void sayHello2(){
    cout<<"hello2222"<<endl;
    sayHello();
    Student2 tian;
    tian.student2Hello();
}

int myAdd2(int a, int b){
    return myAdd(a,b);
}
//
void Student2::student2Hello() {
    cout<<"student22222Hello"<<endl;
}
```

### visual studio调用

### 建立netframework项目

![image-20220415183057359](assets/dll生成与使用/image-20220415183057359.png)

### 将两个dll文件放到debug目录下

![image-20220415183130534](assets/dll生成与使用/image-20220415183130534.png)

### 调用

添加`using System.Runtime.InteropServices;`

导入库文件`[DllImport("DllDemo2.dll")]`

导入函数`private static extern void sayHello2();`

```c#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
//添加相应的库
using System.Runtime.InteropServices;

namespace ConsoleApp3
{
    class Program
    {
        //调用
        [DllImport("DllDemo2.dll")]
        private static extern void sayHello2();
        [DllImport("DllDemo2.dll")]
        private static extern int myAdd2(int a, int b);
        
        static void Main(string[] args)
        {
            //使用
            sayHello2();
            Console.WriteLine(myAdd2(1, 2));
        }
    }
}
```



## QT Creator + msvc

### 生成dll

- 新建dll项目

![image-20220416163601291](assets/dll生成与使用/image-20220416163601291.png)

![image-20220416163629764](assets/dll生成与使用/image-20220416163629764.png)

![image-20220416163646551](assets/dll生成与使用/image-20220416163646551.png)

生成后的结构如下（这里用的的DllDemo1）：

![image-20220416171503348](assets/dll生成与使用/image-20220416171503348.png)

其中_global.h自带两个系统默认导出宏

```c
#ifndef DLLDEMO1_GLOBAL_H
#define DLLDEMO1_GLOBAL_H

#include <QtCore/qglobal.h>

#if defined(DLLDEMO1_LIBRARY)
#  define DLLDEMO1_EXPORT Q_DECL_EXPORT
#else
#  define DLLDEMO1_EXPORT Q_DECL_IMPORT
#endif

#endif // DLLDEMO1_GLOBAL_H

```

- 编写导出函数

头文件：

```c
#ifndef DLLDEMO1_H
#define DLLDEMO1_H

#include "DllDemo1_global.h"

class DLLDEMO1_EXPORT DllDemo1
{
public:
    DllDemo1();
};

//声明导出的函数sayhello
DLLDEMO1_EXPORT void sayHello();
//声明导出的函数myadd
DLLDEMO1_EXPORT int myAdd(int a, int b);

#endif // DLLDEMO1_H

```

源文件：

```c
#include "dlldemo1.h"
#include <iostream>

DllDemo1::DllDemo1()
{
}

//实现函数
void sayHello(){
    std::cout<<"hello, dll in qt"<<std::endl;
}
int myadd(int a, int b){
    std::cout<<"myadd"<<std::endl;
    return a+b;
}

```

构建后会在debug目录下生成**dll和lib文件，同时可能需要用到.h和_global.h文件**

### 调用

#### 新建c++控制台项目

![image-20220416172129620](assets/dll生成与使用/image-20220416172129620.png)

#### 构建项目，生成debug目录

#### 将dll文件放到debug目录

![image-20220416172227912](assets/dll生成与使用/image-20220416172227912.png)

#### 将lib文件放到工程目录下

![image-20220416172258532](assets/dll生成与使用/image-20220416172258532.png)

#### 右键项目，添加lib库

![](assets/dll生成与使用/image-20220416172328609.png)

**选择外部库**

选择刚才的lib文件，取消平台下的linux，mac，取消为debug版本添加d作为哦后缀，点击完成代码就被添加进call.pro

![image-20220416172524995](assets/dll生成与使用/image-20220416172524995.png)

#### 调用

- 方法1：

```c
#include <QCoreApplication>
#include<iostream>
using namespace std;

__declspec(dllimport) void sayHello();
__declspec(dllimport) int myAdd(int a, int b);

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    cout<<myAdd(1,2)<<endl;
    sayHello();
    
    return a.exec();
}

```

- 方法2

将.h和_global.h放入到项目目录下

右键项目，添加两个.h文件

![image-20220416175017723](assets/dll生成与使用/image-20220416175017723.png)

引入头文件

```c
#include <QCoreApplication>
#include<iostream>
//引入头文件
#include "./dlldemo1.h"

using namespace std;

//__declspec(dllimport) void sayHello();
//__declspec(dllimport) int myAdd(int a, int b);

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    cout<<myAdd(1,2)<<endl;
    sayHello();

    return a.exec();
}
```

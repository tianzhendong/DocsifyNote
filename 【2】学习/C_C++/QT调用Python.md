[toc]

# QT调用Python

## 前言

QT上位机程序和python程序交互：

- QT调用python代码
- QT和python通过数据库交互
- QT和python通过网络编程交互

## 环境

电脑：windows11

Anaconda中的opencv虚拟环境，python版本：3.9.7

Anaconda路径：`C:\ProgramData\Anaconda3`

虚拟环境路径：`C:\Users\12038\.conda\envs\opencv\`

## 配置

### QT添加python库文件

在pro文件中添加

```properties
INCLUDEPATH +=C:\Users\12038\.conda\envs\opencv\include   ############# python enviroment
LIBS += -LC:\Users\12038\.conda\envs\opencv\libs\
        -l_tkinter\
        -lpython3\
        -lpython39
```

python路径\include

python路径\libs

### 添加python文件

右键QT项目文件，新建python文件

![image-20220221182133817](https://gitee.com/tianzhendong/img/raw/master//images/202202211821926.png)

编写python代码，这里为`test1.py`

```python
# This Python file uses the following encoding: utf-8

# if __name__ == "__main__":
#     pass
def hello():
    print("hello world!")
```

### QT调用

```c
#include <QCoreApplication>
#include <Python.h>		//添加python
#include <iostream>
using namespace std;
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    Py_Initialize();		//初始化
    if ( !Py_IsInitialized() )
    {
    return -1;
    }
    PyObject* pModule = PyImport_ImportModule("test1");  // 这里的test1就是创建的python文件
    if (!pModule) {
            cout<< "Cant open python file!\n" << endl;
            return -1;
        }
   PyObject* pFunhello= PyObject_GetAttrString(pModule,"hello");  // 这里的hellow就是python文件定义的函数
    if(!pFunhello){
        cout<<"Get function hello failed"<<endl;
        return -1;
    }
    PyObject_CallFunction(pFunhello,NULL);	//调用hello函数
    Py_Finalize();	//释放

   return a.exec();
}
```





## 遇到的问题

https://blog.csdn.net/alxe_made/article/details/83382159

### error: C2238: 意外的标记位于“;”之前

出现的原因：

 **由于QT中定义了slots作为关键了，而python3中有使用slot作为变量，所以有冲突**

解决方法：

在python的object.h中 slots冲突，因此修改object.h

`C:\Users\12038\.conda\envs\opencv\include\object.h`

在`PyType_Slot *slots; `上下加上两句代码，如下

```python
typedef struct{
    const char* name;
    int basicsize;
    int itemsize;
    unsigned int flags;
    #undef slots    //取消宏定义
    PyType_Slot *slots; /* terminated by slot==0. */
    #define slots Q_SLOTS
} PyType_Spec;
```

### 无法打开python39_d.lib

修改`C:\Users\12038\.conda\envs\opencv\include\pyconfig.h`文件，将`python39_d.lib`改为`python39.lib`

```python
#ifdef MS_COREDLL
#       if !defined(Py_BUILD_CORE) && !defined(Py_BUILD_CORE_BUILTIN)
                /* not building the core - must be an ext */
#               if defined(_MSC_VER)
                        /* So MSVC users need not specify the .lib
                        file in their Makefile (other compilers are
                        generally taken care of by distutils.) */
#                       if defined(_DEBUG)
#                               pragma comment(lib,"python39.lib")   //修改后
// #                               pragma comment(lib,"python39_d.lib")   //修改前
#                       elif defined(Py_LIMITED_API)
#                               pragma comment(lib,"python3.lib")
#                       else
#                               pragma comment(lib,"python39.lib")
#                       endif /* _DEBUG */
#               endif /* _MSC_VER */
#       endif /* Py_BUILD_CORE */
#endif /* MS_COREDLL */
```

### error: failed to get the Python codec of the filesystem encoding

https://blog.csdn.net/qq_43302566/article/details/121537908

PYTHONHOME 和 PYTHONPATH 找不到路径，其实类似于JAVA的环境变量

**出现这种原因，我的是因为用了Anaconda的python！！！**

**把PYTHONHOME 和 PYTHONPATH改成conda的目录即可！**

![image-20220221162124213](https://gitee.com/tianzhendong/img/raw/master//images/202202211621272.png)

### Cant open python file!

把python文件手动复制到QT debug文件夹下


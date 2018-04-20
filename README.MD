# 并发学习
## 并发概念
> 同时拥有两个或者多个线程，如果程序在单核处理器上运行，多个线程将交替的换入
或换出内存，这些线程是同时 `存在`的，每个线程都处于执行过程中的某个状态，
如果运行在多核处理器上，此时，程序中的每个线程都将分配到一个处理器核上，
因此可以同时运行
### 并发的优势
1. 同时处理多个请求，相应速度更快；复杂的操作可以分成多个进程同时进行
2. 程序设计在某些情况下更简单，也有更多的选择
3. CPU能在等待IO的时候做一些其他的事情

### 并发的劣势
1. 多个线程共享数据时可能产生于期望不符合的结果
2. 某个操作无法继续进行下去时，会产生活跃性问题，比如死锁、饥饿等问题
3. 线程过多时：CPU频繁切换，调度时间增多；同步机制；消耗过多内存

## 高并发概念
> 高并发 (High Concurrency) 是互联网分布式系统架构中必须考虑的因素之一，
它通常是指，通过设计保证系统能够 `同时并行处理` 很多请求

![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-075459.png)
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-075545.png)
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-075608.png)

## CPU 多级缓存
### 为什么需要CPU多级缓存?
CPU频率太快了，快到主存跟不上，这样在处理器时钟周期内，CPU通常需要等待主存，
造成了资源的浪费。  
所以cache的出现，是为了缓解CPU和主存之间速度的不匹配问题
`(结构: cpu -> cache -> memory)`

###  CPU cache 的意义?
1. `时间局部性`: 如果某个数据被访问，那么在不久的将来它有可能再次被访问
2.  `空间局部性`: 如果某个数据被访问，那么它相邻的数据很快也可能被访问
    
### CPU 缓存一致性(MESI)
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-081135.png)
 
`M` : Modify 修改的  
`E` : Exclusive 独享的  
`S` : Shared 共享的  
`I` : Invalid 失效的  

### CPU 乱序执行优化
> 处理器为了提高运算速度而做出违背代码原有执行顺序的优化

## Java 内存模型 (Java Memory Model, JMM)
> JMM 规范了JVM是如何与计算机协同工作的，规范了一个线程如何和何时能够可以看到
其他线程修改过的后共享变量的值，以及在必须时如何同步地访问共享变量

![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-082132.png)

### Heap (堆)
> 可以动态的分配大小， 垃圾收集器会收走不再使用的数据，缺点是存取速度相对比较慢，主要存储对象

### Stack (栈)  
> Stack 存取速度很快，比寄存器稍慢，但是存储的数据生存期和大小是必须确定的,主要存储基本类型的数据

![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-082933.png)

### 同步的八种操作
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-084251.png)
- `lock`: 作用于主内存的变量，把一个变量标识为一条线程独占的状态
- `unlock`: 作用于主内存的变量，把一个处于锁定状态的变量释放出来，释放后的变量才能被其他线程锁定
- `read`: 作用于主内存的变量，把一个变量的值从主内存传输到工作内存当中，
以便随后的load操作
- `load`: 作用于工作内存的变量，它把read操作从主内存中得到的变量的值放入工作内存的变量副本中
- `use`: 作用于工作内存的变量，把工作内存中的一个变量传递给执行引擎
- `assign` (赋值): 作用于工作内存的变量，它把一个从执行引擎接受到的值赋值给工作内存的变量
- `store`(存储): 作用于工作内存的变量，把工作内存中的一个变量的值传递到主内存中，以便于随后的write操作
- `write`: 作用于主内存的变量，它把store操作从工作内存中一个变量的值传送到主内存的变量中

### 同步规则
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-083906.png)
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-083934.png)
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-084008.png)
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-084029.png) 

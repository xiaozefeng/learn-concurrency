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

## 并发模拟
1. Postman : Http请求模拟工具
2. Apache Bench (AB):  Apache附带的工具，测试网站性能  
``` bash
// -n 一共模拟多少个请求
// -c 允许并发的请求数
ab -n 1000 -c 50 http:localhost:8080/test  

```


![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-20-093701.png)


3. JMeter: Apache 组织开发的压测工具

4. 代码模拟并发 `CountDownLatch` `Semaphore`

## 线程安全性
> 当多个线程访问某个类时，不管运行时缓解采用何种调度方式或者这些进场将如何
交替执行，并且在主调代码中不需要任何额外的同步或协同，这个类都能表现出正切的
行为，那么就称这个类是线程安全的

### 原子性
> 提供了互斥访问，同一时刻只能有一个线程对它进行操作

### 可见性
> 一个线程对主内存的修改能够及时的被其他线程观察到
#### 导致共享变量在线程间不可见的原因:
 - 线程交叉执行
 - 重排序结合线程交叉执行
 - 共享变量更新后的值没有及时在主内存和共内存中及时更新
 
#### volatile 关键字是通过加入内存屏障和禁止重排序优化来实现
- 对volatile变量写操作时，会在写操作后加入一条store屏障指令，将本地共享变量的值刷新到主内存中
- 对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取共享变量
![](http://7xv4mv.com1.z0.glb.clouddn.com/blog/2018-04-21-105718.png)
![](http://7xv4mv.com1.z0.glb.clouddn.com/blog/2018-04-21-105813.png)

### 有序性
> 一个线程观察其他线程中的指令执行顺序，由于指令重排序d额存在，该
观察结果一般杂乱无序
#### happens before 原则
- 程序次序原则: 一个程序内，按照代码顺序，书写在前面的操作先行发生在后面的操作
- 锁定规则: 一个unlock操作先行发生于后面对同一个锁的lock操作
- volatile变量规则: 对一个变量的写操作先行发生于后面对这个变量的读操作
- 传递规则: 如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行
发生于操作C

### CAS (compare and swap)原理
```
 // var1: 当前对象
 // var2:  当前的值
 // var4: 需要加的值
 // var5: 从底层取到的值
 // 逻辑: 当 var2 和 var5 相等值， 将底层的值更新为 var5 + var4 否则一直重试
 public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2);
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
    return var5;
 }    
```

#### AtomicLong 和 LongAdder 区别

#### CAS 的ABA问题
> 是指CAS操作的时候，其他线程将变量A的值改为了B又改回了A
本线程用期望值A与当前变量进行比较的时候，发现A变量没有变,就对A
值进行了swap操作,这个时候其实变量A已经被其他线程修改过了,这与设计思想
是不符合的

#### AtomicStampReference 解决了CAS的ABA问题
> 每次对变量的修改，都会使版本号+1，所以只要当前变量被其他线程修改过
那么变量的版本号也会增加，那么就算重现ABA现象，那么版本号也是
不一样的

#### synchronized 和 Lock 和 Atomic 包对比
> synchronized: 不可中断锁，在jvm层面实现，
适合竞争不激烈的锁，可读性好

> Lock: 可终端锁，多样化同步，竞争激烈时能维持常态

> Atomic: 竞争激烈时能维持常态， 比Lock性能好，不过只能同步一个值


## 安全发布对象
1. 在静态初始化函数中初始化一个对象引用
2. 将对象保存在volatile类型域中或者AtomicReference对象中
3. 将对象的引用保存到某个正确构造对象的final类型域中
4. 将对象的引用保存到一个由锁保护的域中

### 单例模式
1. 饿汉式
2. 懒汉式
3. 双重检测 + volatile (禁止指令重排序)
4. 枚举实现
```java
public class SingletonExample4 {

    /**
     * 私有构造函数
     */
    private SingletonExample4() {

    }

    public static SingletonExample4 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }


    private enum Singleton {
        INSTANCE;

        @Getter
        private SingletonExample4 singleton;

        /** JVM保证只会执行一次 */
        Singleton() {
            this.singleton = new SingletonExample4();
        }
    }

}

```


### 不可变对象
- 对象创建其状态就不可改变
- 对象所有域都是final类型
- 对象是正确创建的，(在对象创建期间，this 引用没有逸出)



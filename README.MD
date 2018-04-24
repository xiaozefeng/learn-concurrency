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

### 线程封闭

### 线程不安全的写法
- StringBuilder  -> StringBuffer
- SimpleDateFormatter -> JodaTime
- ArrayList, HashSet, HashMap
- if(condition(a)) { handle(a)}

### 同步容器



### 并发容器 (J.U.C)
![](http://7xv4mv.com1.z0.glb.clouddn.com/blog/2018-04-22-WechatIMG16.jpg)
#### ArrayList -> CopyOnWriteArrayList
> 写操作时复制，当有新的元素写入时先从原有的数组拷贝一份出来，在新的数组上作写操作
写完之后，再讲原来的数组指向新数组。
缺点1: 因为需要拷贝数组，所以在元素较多的情况下，可能发生GC
缺点2: 不能用于实时读的场景，因为拷贝数组和新增元素都需要时间，当我们调用set操作后读取到的数据可能
还是旧的；虽然CopyOnWriteArrayList能做到最终一致性，但是没法满足我们实时性的要求

`CopyOnWriteArrayList 更适合读多写少的场景`

#### HashSet、TreeSet -> CopyOnWriteArraySet、 ConcurrentSkipListSet
> CopyOnWriteArraySet 原理和 CopyOnWriteArrayList 一样
ConcurrentSkipListSet 的add、remove、都是原子性的，但是addAll、removeAll不是原子性操作的，
需要自动手动加锁

#### HashMap、TreeMap -> ConcurrentHashMap、ConcurrentSkipListMap


### 安全共享对象
> 线程限制: 一个被线程限制的对象，由线程独占，并且只能被占有它的线程修改
> 共享只读: 一个共享只读对象，在没有额外同步的情况下，可以被多个线程并发访问，
但是任何线程都不能修改它
> 线程安全对象: 一个线程安全的对象或容器，在内部通过同步机制来保证线程安全，所以其他线程
无需额外的同步就可以通过公共接口随意的访问它
> 被守护对象: 被守护的对象只能通过获取特定的锁来访问它


### 同步器 AQS
- 使用Node实现FIFO(First In First Out)队列，可用用于构建锁或者其他同步装置的基础框架
- 利用了一个int类型表示状态
- 使用方法是继承
- 子类通过继承并通过实现它的方法来管理其状态，{acquire 和 release} 的方法操作状态
- 可以同时实现排它锁和共享锁模式(独占、共享)

#### AQS实现原理
> 内部维护一个CLH队列来管理锁，线程会尝试获取锁，如果失败就讲当前线程以及等待等信息包成一个Node节点加入到syncQueue 中，
接着不断循环获取锁，它的条件是当前节点为head的节点直接后进才会尝试，如果失败就会阻塞自己，直到自己被唤醒，而当持有锁的
线程释放锁的时候，会唤醒队列中的后进线程

### AQS同步组件
#### CountDownLatch
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-23-041929.png)  
- 使用场景：
    1. 某些程序的执行需要等待某个条件完成后才能继续执行后续的操作

#### Semaphore
> 控制同一时间内允许线程的个数
#### CyclicBarrier
#### ReentrantLock
- 可指定是公平锁还是非公平锁
- 提供了一个Condition类，可以分组唤醒需要唤醒的线程
- 提供能够中断等待锁的线程的机制，lock.lockInterruptibly()

#### Condition
#### FutureTask


### 使用锁时一些建议
1. 当竞争者不多的时候使用synchronized比较好
2. 当竞争者不少，但是增长的趋势是能够预估的，这个时候ReentrantLock是一个很多的选择

### FutureTask
### BlockingQueue
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-23-105440.png)
#### ArrayBlockingQueue
#### DelayQueue
#### LinkedBlockingQueue
#### PriorityBlockingQueue
#### SynchronousQueue
#### HashMap 
> HashMap resize()在多线程情况下可能造成死循环
#### ConcurrentHashMap
> JDK1.7 中使用分段锁技术: 最外层是一个segment数组
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-04-24-021952.png)

### 线程池
- 重用存在的线程，减少对象的创建，消亡的消亡，性能佳
- 可有效控制最大并发线程数，提高系统资源利用率，同时可以避免过多的资源竞争，避免阻塞
- 提供定时执行、定期执行、单线程、并发数控制等功能
#### 构造方法
```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler){}
```
##### 规则
> 当前线程数不大于corePoolSize时，不管其他线程是否空闲，都会创建新的线程处理任务
> 当 corePoolSize < 线程数 < maximumPoolSize, 如果workQueue 没满，那么放入workQueue ，否则创建新的线程
> 当corePoolSize 的设置和 maximumPoolSize 是一样的时候，看workQueue是否满了，如果没满则放入队列，等下空闲线程来处理,当workQueue满的时候，使用handler 来处理
> 当workQueue是无界队列时，那么maximumPoolSize无效，线程池中的最大线程数为corePoolSize, 当所有corePoolSize都在做任务的时候，请求的任务都会放入workQueue
> 当workQueue是有界队列时，可以将最大线程数限制为maximumPoolSize， 可以降低资源的消耗，但是没那么大的吞吐量
> keepAliveTime: 当线程数量超过了corePoolSize的时候，这个时候又没有任务提交，那么在一定时间后，会销毁corePoolSize数量以外的线程数

##### 如何合理设置
> 降低系统资源的消耗: 较大的队列容量，和较小的线程数量，这样会降低线程处理任务的吞吐量
> 如果我们的方法经常发生阻塞，那么可以重新设置线程池的容量
> CPU密集型任务，就需要尽量压榨CPU，参考值可以设置为NCPU+1
> IO密集型人物，参考值可以设置为2 * NCPU


### 死锁
>两个或者两个以上的线程因争夺资源而造成的一种互相等待的现象，如果没有外力作用，
它们都将无法运行下去，此时，我们就称系统处于死锁状态，或者系统产生了死锁

#### 死锁发生必须具备的条件
- 互斥条件
- 请求和保持条件
- 不剥夺条件
- 环路等待条件

### 多线程开发最佳实践
- 使用本地变量
- 使用不可变类
- 最下化锁的作用域范围: `S = 1/(1-a+a/n)`
- 使用线程池的Executor，而不是直接new Thread执行
- 宁可使用同步，也不要使用线程的wait和notify方法
- 使用BlockingQueue实现生产-消费模式
- 使用并发集合而不是加了锁的同步集合
- 使用Semaphore创建有界的访问
- 宁可使用同步代码块，也不使用同步方法
- 避免使用static变量，如果需要使用的话也使用final修饰


package concurrency;

/**
 * Created by gang.qin on 2015/10/29.
 * 线程间通讯
 * synchronized 关键字主要确保多个线程在同一时刻，只能有一个线程处于方法或者同步块中
 * 1. 修饰同步块，使用monitorenter、monitorexit这两个指令实现Synchronized
 * 2. 修饰方法，使用ACC_SYNCHRONIZED 修饰符实现Synchronized
 */
public class Synchronized {
    public static void main(String[] args) {
        // 修饰同步块， 对 Synchronized class 对象进行加锁
        synchronized (Synchronized.class) {
        }

        // 静态同步方法， 对Synchronized class 对象进行加锁
        m();
    }

    // 修饰同步方法
    public static synchronized void m() {
    }
}

/*
在 Synchronized.class 同级目录执行 javap -v Synchronized.class, 输出信息如下：

Classfile /f:/Intellij/monkey/target/classes/concurrency/Synchronized.class
  Last modified 2015-10-29; size 503 bytes
  MD5 checksum 480770e65451c58c2633ecb8879b1e7b
  Compiled from "Synchronized.java"
public class concurrency.Synchronized
  SourceFile: "Synchronized.java"
  minor version: 0
  major version: 49
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#19         //  java/lang/Object."<init>":()V
   #2 = Class              #20            //  concurrency/Synchronized
   #3 = Methodref          #2.#21         //  concurrency/Synchronized.m:()V
   #4 = Class              #22            //  java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               LocalVariableTable
  #10 = Utf8               this
  #11 = Utf8               Lconcurrency/Synchronized;
  #12 = Utf8               main
  #13 = Utf8               ([Ljava/lang/String;)V
  #14 = Utf8               args
  #15 = Utf8               [Ljava/lang/String;
  #16 = Utf8               m
  #17 = Utf8               SourceFile
  #18 = Utf8               Synchronized.java
  #19 = NameAndType        #5:#6          //  "<init>":()V
  #20 = Utf8               concurrency/Synchronized
  #21 = NameAndType        #16:#6         //  m:()V
  #22 = Utf8               java/lang/Object
{
  public concurrency.Synchronized();
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 6: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0       5     0  this   Lconcurrency/Synchronized;

  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=3, args_size=1
         0: ldc_w         #2                  // class concurrency/Synchronized
         3: dup
         4: astore_1
         5: monitorenter                      // monitorenter: 监视器进入，获取锁
         6: aload_1                           // 同步块使用monitorenter、monitorexit这两个指令实现 Synchronized
         7: monitorexit                       // monitorexit: 监视器退出，释放锁
         8: goto          16
        11: astore_2
        12: aload_1
        13: monitorexit
        14: aload_2
        15: athrow
        16: invokestatic  #3                  // Method m:()V
        19: return
      Exception table:
         from    to  target type
             6     8    11   any
            11    14    11   any
      LineNumberTable:
        line 9: 0
        line 11: 6
        line 14: 16
        line 15: 19
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      20     0  args   [Ljava/lang/String;

  public static synchronized void m();        // 同步方法，用ACC_SYNCHRONIZED 修饰符实现Synchronized
    flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
    Code:
      stack=0, locals=0, args_size=0
         0: return
      LineNumberTable:
        line 19: 0
}

 */
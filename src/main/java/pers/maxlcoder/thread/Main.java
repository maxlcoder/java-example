package pers.maxlcoder.thread;

public class Main {
     public static void main(String[] args) {
         String name1 = "张三";
         String name2 = "李四";
         // 1. 继承 Thread 类
         Thread t1 = new MyThread(name1); // 定义线程
         Thread t2 = new MyThread(name2); // 定义线程
         t2.setPriority(1);
         t1.start(); // 启动线程
         t2.start(); // 启动线程

         // 2. 向 Thread 实例传入 Runnable 对象，Runnable 对象实现了 Runnable 接口
         Thread t3 = new Thread(new MyRunnable());
         t3.start();

         // 3. 函数注入
         Thread t4 = new Thread(() -> {
             try {
                 Thread.sleep(6000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println("函数注入线程执行体");
         });
         t4.setDaemon(true);
         t4.start();
//         try {
//             t4.join();
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

         System.out.println("end");

     }
}

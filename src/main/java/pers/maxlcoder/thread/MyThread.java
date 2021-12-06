package pers.maxlcoder.thread;

public class MyThread extends Thread {

    private String name = "";

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "实现 Thread run 方法工作");
    }
}

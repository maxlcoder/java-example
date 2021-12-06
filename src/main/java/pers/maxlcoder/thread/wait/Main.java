package pers.maxlcoder.thread.wait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        TaskQueue q = new TaskQueue();
        ArrayList<Thread> ts = new ArrayList<Thread>();
        for (int i = 0; i < 5; i ++) {
            Thread t = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            String s = q.getTask();
                            System.out.println("get task" + s);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            t.start();
            ts.add(t);
        }

        Thread add = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                q.addTask(s);
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {

                }
            }

        });

        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread t : ts) {
            t.interrupt();
        }


    }

}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }
}

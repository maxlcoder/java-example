package pers.maxlcoder.thread.relock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private int count;
    private final Lock lock = new ReentrantLock();

    public void add(int n) {
        lock.lock();
        if (lock.tryLock()) {
            try {
                count += n;
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Main an = new Main();
        Thread a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                an.add(1);
                System.out.println(an.count);
            }
        });
        Thread b = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                an.add(2);
                System.out.println(an.count);
            }
        });

        a.start();
        b.start();
        a.join();
        b.join();
    }
}

class TaskQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private Queue<String> q = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            q.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() {
        lock.lock();
        try {
            try {
                while (q.isEmpty()) {
                    condition.await();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
        return q.remove();
    }
}

package myjava;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class Demo1 {
    public static int num = 100;
    public static StampedLock stampedLock = new StampedLock();

    //写锁演示
    public void write(){
        // 返回一个用于解锁或模式转换的stamp
        long stamp = stampedLock.writeLock();
        try {
            num ++;
        }finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    //读锁演示，读未完成，无法获得写锁
    public void read(){
        long stamp = stampedLock.readLock();
        try {
            System.out.println(num);
            Thread.currentThread().sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }



    public static void main(String[] args) {
        Demo1 d1 = new Demo1();
        new Thread(()->{
            d1.read();
        }).start();

        new Thread(()->{
            d1.write();
        }).start();

        System.out.println(num);
    }
}

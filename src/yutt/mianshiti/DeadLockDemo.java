package yutt.mianshiti;
//死锁

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable
{
    private String lock1;
    private String lock2;
    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }
    @Override
    public void run() {
        synchronized (lock1)
        {
            System.out.println(Thread.currentThread().getName()+"\t自己持有:"+lock1+"\t尝试获得："+lock2);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2)
            {
                System.out.println(Thread.currentThread().getName()+"\t自己持有:"+lock2+"\t尝试获得："+lock1);
            }
        }
    }
}
public class DeadLockDemo
{
    /*
    * 死锁是制两个或者两个以上的线程执行时对资源进行互相等待的现象，若无外力干涉都无法推进下去*/
    public static void main(String[] args) {
        String lock1="lock1";
        String lock2="lock2";
        new Thread(new HoldLockThread(lock1,lock2),"线程1").start();
        new Thread(new HoldLockThread(lock2,lock1),"线程2").start();
    }
}

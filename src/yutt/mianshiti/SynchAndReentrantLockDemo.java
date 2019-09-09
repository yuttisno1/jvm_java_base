package yutt.mianshiti;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//线程操作资源类
class ShareResorce
{
    private int num=1;//A->B->C->D

    private Lock lock=new ReentrantLock();

    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    private Condition c4=lock.newCondition();

    //打印五次
    public void print_5() {
       lock.lock();
       try {
            //1.判断
            while (num!=1) {
                c1.await();
            }
            //2 干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 通知
           num=2;
           c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    //打印五次
    public void print_10() {
        lock.lock();
        try {
            //1.判断
            while (num!=2) {
                c2.await();
            }
            //2 干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 通知
            num=3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    //打印五次
    public void print_15() {
        lock.lock();
        try {
            //1.判断
            while (num!=3) {
                c3.await();
            }
            //2 干活
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 通知
            num=4;
            c4.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    //打印五次
    public void print_20() {
        lock.lock();
        try {
            //1.判断
            while (num!=4) {
                c4.await();
            }
            //2 干活
            for (int i = 1; i <=20 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 通知
            num=1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
public class SynchAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResorce sr=new ShareResorce();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sr.print_5();
            }
        },"线程1").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sr.print_10();
            }
        },"线程2").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sr.print_15();
            }
        },"线程3").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sr.print_20();
            }
        },"线程4").start();
    }
}

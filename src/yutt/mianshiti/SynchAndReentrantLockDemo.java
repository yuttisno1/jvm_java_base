package yutt.mianshiti;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//�̲߳�����Դ��
class ShareResorce
{
    private int num=1;//A->B->C->D

    private Lock lock=new ReentrantLock();

    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    private Condition c4=lock.newCondition();

    //��ӡ���
    public void print_5() {
       lock.lock();
       try {
            //1.�ж�
            while (num!=1) {
                c1.await();
            }
            //2 �ɻ�
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 ֪ͨ
           num=2;
           c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    //��ӡ���
    public void print_10() {
        lock.lock();
        try {
            //1.�ж�
            while (num!=2) {
                c2.await();
            }
            //2 �ɻ�
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 ֪ͨ
            num=3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    //��ӡ���
    public void print_15() {
        lock.lock();
        try {
            //1.�ж�
            while (num!=3) {
                c3.await();
            }
            //2 �ɻ�
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 ֪ͨ
            num=4;
            c4.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    //��ӡ���
    public void print_20() {
        lock.lock();
        try {
            //1.�ж�
            while (num!=4) {
                c4.await();
            }
            //2 �ɻ�
            for (int i = 1; i <=20 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 ֪ͨ
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
        },"�߳�1").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sr.print_10();
            }
        },"�߳�2").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sr.print_15();
            }
        },"�߳�3").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sr.print_20();
            }
        },"�߳�4").start();
    }
}

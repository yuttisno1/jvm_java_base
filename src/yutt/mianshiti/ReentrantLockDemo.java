package yutt.mianshiti;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone=new Phone();
        new Thread(()->{
           try{
               phone.sendSMS();
           }
           catch (Exception e)
           {
               e.getMessage();
           }
        },"t1").start();

        new Thread(()->{
            try{
                phone.sendSMS();
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        },"t2").start();
//Lock unLock

        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3=new Thread(phone,"t3");
        Thread t4=new Thread(phone,"t4");
        t3.start();
        t4.start();
    }
}
class Phone implements Runnable {
    public synchronized void sendSMS()throws Exception
    {
        //System.out.println(Thread.currentThread().getId()+"\t 发送短信");
        System.out.println(Thread.currentThread().getName()+"\t 发送短信");
        sendEmail();
    }
    public synchronized void sendEmail()throws Exception
    {
        //System.out.println(Thread.currentThread().getId()+"\t 发送邮件");
        System.out.println(Thread.currentThread().getName()+"\t 发送邮件");
    }

Lock lock=new ReentrantLock();
@Override
    public void run() {
        get();
    }
    public void get()
    {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t get()方法");
            set();
        }
        finally
        {
            lock.unlock();
            lock.unlock();
        }
    }
    public void set()
    {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t set()方法");
        }
        finally
        {
            lock.unlock();
        }
    }
}
package yutt.mianshiti;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData
{
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void increment() throws Exception
    {
        lock.lock();
        try {
            //1.判断
            while (number!=0)
            {
                condition.await();//2.0 版本写法
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知唤醒
            condition.signalAll();//唤醒
        }
      catch (Exception e)
      {
          e.printStackTrace();
      }
      finally {
            lock.unlock();
        }
    }
    public void decrement() throws Exception
    {
        lock.lock();
        try {
            //1.判断
            while (number==0)
            {
                condition.await();//2.0 版本写法
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知唤醒
            condition.signalAll();//唤醒
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
//生产者消费者-传统版
public class ProCust_Tradition_Demo {
    public static void main(String[] args) {
        ShareData sd=new ShareData();
       new Thread(()->{
           for (int i = 1; i <=5 ; i++) {
               try {
                   sd.increment();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       },"AA").start();


        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    sd.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}

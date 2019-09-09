package yutt.mianshiti;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<String> sq=new SynchronousQueue<>();
        new Thread(()->{
            try {
               System.out.println(Thread.currentThread().getName()+"\t ----a");
                sq.put("a");

                System.out.println(Thread.currentThread().getName()+"\t ----b");
                sq.put("b");

                System.out.println(Thread.currentThread().getName()+"\t ----c");
                sq.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"First").start();

        new Thread(()->{
            try {
               try{ TimeUnit.SECONDS.sleep(2);}
               catch (InterruptedException e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+sq.take());

                try{ TimeUnit.SECONDS.sleep(2);}
                catch (InterruptedException e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+sq.take());

                try{ TimeUnit.SECONDS.sleep(2);}
                catch (InterruptedException e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+sq.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Second").start();
    }
}

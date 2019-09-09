package yutt.mianshiti;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore se=new Semaphore(3);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try{
                    se.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    try{
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(Thread.currentThread().getName()+"\t 停车3秒，出去");

                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    se.release();
                }
            },String.valueOf(i)).start();
        }
    }
}

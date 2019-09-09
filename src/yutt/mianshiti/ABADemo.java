package yutt.mianshiti;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicReference<Integer> atomic=new AtomicReference<>(100);
    //????
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        new Thread(()->{
            //t1 ABA
            System.out.println("==================ABA ???======================");
            atomic.compareAndSet(100,101);
            atomic.compareAndSet(101,100);
        },"t1").start();
        new Thread(()->{
            //????????,t2??t1??????
            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(atomic.compareAndSet(100,2019)+"\t"+ atomic.get());
        },"t2").start();

        try{
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        // t2????
        System.out.println("==================ABA ????====================== ");
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t t3 ????????"+stamp);
            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t t3 ????????"+atomicStampedReference.getStamp());


            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t t3 ?????????"+atomicStampedReference.getStamp());


        },"t3").start();
        //t3????
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t t4 ????????"+stamp);
            try{
                TimeUnit.SECONDS.sleep(3);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
           boolean result= atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t t4 ??????????"+result+"\t ????????"+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t t4 ????"+atomicStampedReference.getReference());

        },"t4").start();
        new Thread(()->{},"t5").start();
    }
}

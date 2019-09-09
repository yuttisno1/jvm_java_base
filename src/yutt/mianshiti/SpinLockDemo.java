package yutt.mianshiti;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*
* 自写一个自旋锁
* */

    public class SpinLockDemo {
        //原子引用线程
        AtomicReference<Thread> atomicReference=new AtomicReference<>();
        public void myLock()
        {
            Thread thread=Thread.currentThread();
            System.out.println(thread.getName()+"\t come in o(n_n)o");
            int i=0;
            while(!atomicReference.compareAndSet(null,thread))
            {
              //  myUnlock();
                i++;
                System.out.println(thread.getName()+"\t"+i);
            }

        }
        public void myUnlock()
        {
            Thread thread=Thread.currentThread();
            atomicReference.compareAndSet(thread,null);
            System.out.println(thread.getName()+"\t unlock o(n_n)o");
        }
        public static void main(String[] args) {
            SpinLockDemo a=new SpinLockDemo();
            new Thread(()->{
                a.myLock();
                try{
                    TimeUnit.SECONDS.sleep(5);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                a.myUnlock();
            },"AA").start();

            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            new Thread(()->{
                a.myLock();
                a.myUnlock();
            },"BB").start();
        }
}

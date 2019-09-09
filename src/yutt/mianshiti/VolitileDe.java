package yutt.mianshiti;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ManyXiancheng{
   volatile int n=0;
    public void add60()
    {
        this.n=60;
    }
    //可以加 synchronize，但是比较浪费，此处可不使用
    public  void addPlus()
    {
        n++;
    }
    AtomicInteger ato=new AtomicInteger();
    public void addAtomic()
    {
        ato.getAndIncrement();
    }
}
/**
 * 1.   seeOkByVolit() 验证volatile的可见性
 * （1）没有volatile关键字修饰，没有可见性，有了volatile，可以解决可见性问题
 * 2.   验证volatile的不保证原子性
 */
public class VolitileDe
{
    public static void main(String[] args) {
       // seeOkByVolit();
        seeAndNotAee();

    }

    public static void seeAndNotAee() {
        ManyXiancheng m=new ManyXiancheng();
        for(int i=1;i<=20;i++)
        {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    m.addPlus();
                    m.addAtomic();
                }
            },String.valueOf(i)).start();
        }
//等待上面的20个线程全部计算完成，再用main线程取得最终的结果值是多少
        //暂停一会线程
        while (Thread.activeCount()>2)
        {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally number value:"+m.n);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger  value:"+m.ato);
    }

    //19915 19985 18946 18364
    public static void seeOkByVolit() {
        ManyXiancheng m=new ManyXiancheng();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try
            {
                TimeUnit.SECONDS.sleep(3);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            m.add60();
            System.out.println(Thread.currentThread().getName()+"\t updated number: "+m.n);
        },"AAA").start();
        int h=0;
        while (m.n==0)
        {
         /*   h++;
            System.out.println("h="+h);
            System.out.println();*/
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}
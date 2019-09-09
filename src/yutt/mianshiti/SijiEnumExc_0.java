package yutt.mianshiti;

import java.util.concurrent.CountDownLatch;

public class SijiEnumExc_0 {
    public static void main(String[] args) throws Exception {
        CountDownLatch c=new CountDownLatch(4);
        for (int i = 1; i <=4 ; i++) {
            final int k=i;
            new Thread(()->{
                c.countDown();
                hh(Thread.currentThread().getName(),k);
              //  System.out.println(Thread.currentThread().getName()+"\t"+Jijie.Jijie_Enum(k).getValue2());
                c.countDown();
            },Jijie.Jijie_Enum(k).getValue()).start();
        }
        c.await();
        System.out.println(Thread.currentThread().getName()+"\t ´ºÏÄÇï¶¬½áÊø");
    }
    public static synchronized String hh(String name,int k)
    {
        System.out.println(name+"\t"+k);
        System.out.println(name+"\t"+Jijie.Jijie_Enum(k).getValue2());
        System.out.println(name+"\t"+Jijie.Jijie_Enum(k).value);
        System.out.println("***************************");
        return null;
    }
}

package yutt.mianshiti;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CeShiLuanMa_lizi {

    public static Map thread_longzhu(int longzhu)
    {
        Map map=new HashMap();
        CyclicBarrier cb=new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        for (int i = 1; i <=longzhu ; i++) {
            final int n=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 收集到第"+n+"颗龙珠。");
                //blockingQueue.offer(Thread.currentThread().getName()+"\t 收集到第"+n+"颗龙珠。");
                String a=Thread.currentThread().getName()+"\t 收集到第"+n+"颗龙珠。";
                map.put(n,a);
                try
                {cb.await();}
                catch (InterruptedException e)
                {e.printStackTrace();}
                catch (BrokenBarrierException e)
                {e.printStackTrace();}
            },String.valueOf(i)).start();
        }
        return map;
    }
}

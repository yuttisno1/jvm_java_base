package yutt.mianshiti;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        //函数式接口
        CyclicBarrier cb=new CyclicBarrier(7);
        for (int i = 1; i <=7 ; i++) {
            final int n=i;
            Thread th=new Thread();
            System.out.println( th.getName()+"\t 收集到第"+n+"颗龙珠。");
            try
            {
                cb.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (BrokenBarrierException e)
            {
                e.printStackTrace();
            }
            th.start();
        }
    }
}

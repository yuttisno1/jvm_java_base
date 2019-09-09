package yutt.mianshiti;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        //����ʽ�ӿ�
        CyclicBarrier cb=new CyclicBarrier(7);
        for (int i = 1; i <=7 ; i++) {
            final int n=i;
            Thread th=new Thread();
            System.out.println( th.getName()+"\t �ռ�����"+n+"�����顣");
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

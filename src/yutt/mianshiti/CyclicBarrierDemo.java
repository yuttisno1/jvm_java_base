package yutt.mianshiti;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //����ʽ�ӿ�
        CyclicBarrier cb=new CyclicBarrier(7,()->{
            System.out.println("�ٻ�����");
        });
        for (int i = 1; i <=7 ; i++) {
            final int n=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t �ռ�����"+n+"�����顣");
                try
                {cb.await();}
                catch (InterruptedException e)
                {e.printStackTrace();}
                catch (BrokenBarrierException e)
                {e.printStackTrace();}
            },String.valueOf(i)).start();
        }
    }
}

package yutt.mianshiti;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ManyXiancheng{
   volatile int n=0;
    public void add60()
    {
        this.n=60;
    }
    //���Լ� synchronize�����ǱȽ��˷ѣ��˴��ɲ�ʹ��
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
 * 1.   seeOkByVolit() ��֤volatile�Ŀɼ���
 * ��1��û��volatile�ؼ������Σ�û�пɼ��ԣ�����volatile�����Խ���ɼ�������
 * 2.   ��֤volatile�Ĳ���֤ԭ����
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
//�ȴ������20���߳�ȫ��������ɣ�����main�߳�ȡ�����յĽ��ֵ�Ƕ���
        //��ͣһ���߳�
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
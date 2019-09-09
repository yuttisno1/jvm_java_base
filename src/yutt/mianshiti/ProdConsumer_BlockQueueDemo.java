package yutt.mianshiti;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//�̲߳�����Դ�࣬����֪ʶ�㴮������
//������������ ��Ϣ�м��
//volatile/CAS/atomicInterger/BlockQueue/�߳̽���/ԭ������
class MyResouce{
    //�߲����µĳ�����Ҫ�� volatile
    private volatile boolean Flag=true;//Ĭ�Ͽ�������������������
   //���߳�����£���Ҫʹ��i++����++i,����Լ�����
   private AtomicInteger atomicInteger=new AtomicInteger();
    /*
   *�������е���أ�ʹ�������е�ʵ�ַ�ʽ��7��ȫ�á�
   * ͳͳ�����ͨ�ã�д�ӿڣ���дʵ���ࡣ����ע�뷽��
   * ���ӿڣ���Ҫ���ࣻ���ݵ����ʵ�ַ���
    *  */
    BlockingQueue<String> blockingQueue=null;
    //�����ӵ����
    //����ע�봫�ӿڣ���Ҫд�������ӿڵ���չ�ԣ������ԣ�ͨ������ǿ��
    //���δ��ӿڣ��˴���BlockingQueue�ӿ�
    //BlockingQueue�ӿ�
    public MyResouce(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    //����lock
//    private Lock lock=new ReentrantLock();
    //����
    public void myProd() throws Exception
    {
        String data=null;
        boolean reValue;
        while (Flag)
        {
            //ԭ�Ӱ��i++,��int���ͻ�Ϊstring
            data=atomicInteger.incrementAndGet()+"";
            reValue=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(reValue)
            {
                System.out.println(Thread.currentThread().getName()+"\t �������"+data+"�ɹ�");
            }
            else
            {
                System.out.println(Thread.currentThread().getName()+"\t �������"+data+"ʧ��");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t ��ͣ����ʾFlag=false,ֹͣ����");
    }
    //����
    public void myConsumer() throws Exception
    {
        String result=null;
        boolean reValue;
        while (Flag)
        {
            //ԭ�Ӱ��i++,��int���ͻ�Ϊstring

            result=blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(result==null || result.equalsIgnoreCase(""))
            {
                Flag=false;
                System.out.println(Thread.currentThread().getName()+"\t ���������������յ����⣬�����˳���");
                System.out.println("----��-----");
                System.out.println("----��-----");
                System.out.println("----��-----");

                return;
            }

                System.out.println(Thread.currentThread().getName()+"\t ���Ѷ���"+result+"�ɹ�");
        }
    }
    public void stop()throws Exception
    {
        this.Flag=false;
        //stop();
    }
}
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) throws Exception {
        //ArrayBlockingQueue��ArrayBlockingQueue����������
 //       BlockingQueue<String> bq=new ArrayBlockingQueue<>(10);
        BlockingQueue<String> bq=new LinkedBlockingQueue<>(10);
        MyResouce my=new MyResouce(bq);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t�����߳�����");
            try {
                my.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"������").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t�����߳�����");
            try {
                my.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"������").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------");
        System.out.println("5��ʱ�䵽��main�߳̽�ͣ�������");
        my.stop();
    }
}

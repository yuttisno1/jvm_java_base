package yutt.mianshiti;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//线程操作资源类，所有知识点串联起来
//生产者消费者 消息中间件
//volatile/CAS/atomicInterger/BlockQueue/线程交互/原子引用
class MyResouce{
    //高并发下的程序需要加 volatile
    private volatile boolean Flag=true;//默认开启，进行生产加消费
   //多线程情况下，不要使用i++或者++i,会给自己埋雷
   private AtomicInteger atomicInteger=new AtomicInteger();
    /*
   *阻塞队列的落地，使用其所有的实现方式，7个全用。
   * 统统适配和通用，写接口，不写实现类。构造注入方法
   * 传接口，不要传类；传递的落地实现方法
    *  */
    BlockingQueue<String> blockingQueue=null;
    //高连接低耦合
    //构造注入传接口，不要写死，传接口的拓展性，适配性，通用性增强。
    //传参穿接口，此处传BlockingQueue接口
    //BlockingQueue接口
    public MyResouce(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    //不用lock
//    private Lock lock=new ReentrantLock();
    //生产
    public void myProd() throws Exception
    {
        String data=null;
        boolean reValue;
        while (Flag)
        {
            //原子版的i++,将int类型换为string
            data=atomicInteger.incrementAndGet()+"";
            reValue=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(reValue)
            {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }
            else
            {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 叫停，表示Flag=false,停止生产");
    }
    //消费
    public void myConsumer() throws Exception
    {
        String result=null;
        boolean reValue;
        while (Flag)
        {
            //原子版的i++,将int类型换为string

            result=blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(result==null || result.equalsIgnoreCase(""))
            {
                Flag=false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟诶呦收到蛋糕，消费退出。");
                System.out.println("----消-----");
                System.out.println("----费-----");
                System.out.println("----者-----");

                return;
            }

                System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
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
        //ArrayBlockingQueue和ArrayBlockingQueue方法都可以
 //       BlockingQueue<String> bq=new ArrayBlockingQueue<>(10);
        BlockingQueue<String> bq=new LinkedBlockingQueue<>(10);
        MyResouce my=new MyResouce(bq);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                my.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"生产者").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                my.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费者").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------");
        System.out.println("5秒时间到，main线程叫停，活动结束");
        my.stop();
    }
}

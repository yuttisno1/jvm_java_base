package yutt.mianshiti;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println( Runtime.getRuntime().availableProcessors());
    //    Runtime.getRuntime().availableProcessors();
     //   threadJDKMorenPool();
        //��д�̳߳� һ��������ڣ�Ĭ�Ͽ���2�����ڣ��Ⱥ�����������λ��10��ҵ��
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
               // new ThreadPoolExecutor.AbortPolicy());
               // new ThreadPoolExecutor.CallerRunsPolicy());
                // new ThreadPoolExecutor.DiscardOldestPolicy());
                new ThreadPoolExecutor.DiscardPolicy());
        try{
            for (int i = 1; i <=10; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t ����ҵ��");
                });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            threadPoolExecutor.shutdown();
        }
    }

    public static void threadJDKMorenPool() {
        //  System.out.println(Runtime.getRuntime().availableProcessors());
        //newFixedThreadPool
        /*???????????
        ????????????java?????????????? Executors
        * ???????Thread??
        * ????????runnable????????????????????
        * ?????????callable????????????????????
        * */
//        ExecutorService executorService= Executors.newFixedThreadPool(5);//һ������߳�
//        ExecutorService executorService= Executors.newSingleThreadExecutor();//һ��һ���߳�
        ExecutorService executorService= Executors.newCachedThreadPool();//һ�ض��߳�
        //???????
        try{
            for (int i = 0; i <10 ; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t ����ҵ��");
                });
//                try{
//                    TimeUnit.SECONDS.sleep(3);
//                }
//                catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            executorService.shutdown();
        }
    }
}

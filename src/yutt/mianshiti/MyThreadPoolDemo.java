package yutt.mianshiti;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println( Runtime.getRuntime().availableProcessors());
    //    Runtime.getRuntime().availableProcessors();
     //   threadJDKMorenPool();
        //自写线程池 一共五个窗口，默认开启2个窗口，等候厅有三个座位，10个业务
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
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
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
//        ExecutorService executorService= Executors.newFixedThreadPool(5);//一池五个线程
//        ExecutorService executorService= Executors.newSingleThreadExecutor();//一池一个线程
        ExecutorService executorService= Executors.newCachedThreadPool();//一池多线程
        //???????
        try{
            for (int i = 0; i <10 ; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
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

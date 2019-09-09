package yutt.mianshiti;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/*
�̳߳�
������
* */
//Runnable�޷���ֵ
//class MyThread0 implements Runnable
//{
//    @Override
//    public void run() {
//    }
//}
class MyThread1 extends FutureTask
{
    public MyThread1(Callable callable) {
        super(callable);
    }

    public MyThread1(Runnable runnable, Object result) {
        super(runnable, result);
    }
}

class MyThread implements Callable<Integer>
{
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t************************");
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 1023;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        MyThread my=new MyThread();
//        FutureTask<Integer> futureTask=new FutureTask<>(my);
//        Thread th=new Thread(futureTask,"AA");
//        th.start();
//        System.out.println("*************return:"+futureTask.get());

        MyThread my=new MyThread();
//        //�������߳�һģһ��ʱ���Ḵ�ò������¼���
////        FutureTask<Integer> futureTask=new FutureTask<>(my);
////        new Thread(futureTask,"AA").start();
////        new Thread(futureTask,"BB").start();

        //�������߳�һģһ��ʱ�����õĲ�����Ҫ��ͬ
        FutureTask<Integer> futureTask=new FutureTask<>(my);
        FutureTask<Integer> futureTask1=new FutureTask<>(my);

        new Thread(futureTask,"AA").start();
        new Thread(futureTask1,"BB").start();

        System.out.println(Thread.currentThread().getName()+"--------------");
        int re1=10000;
        while(!futureTask.isDone())
        {
        }
        int re2=futureTask.get();
        System.out.println("*************return:"+(re1+re2));
    }
}

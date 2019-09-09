package yutt.mianshiti;
//����

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable
{
    private String lock1;
    private String lock2;
    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }
    @Override
    public void run() {
        synchronized (lock1)
        {
            System.out.println(Thread.currentThread().getName()+"\t�Լ�����:"+lock1+"\t���Ի�ã�"+lock2);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2)
            {
                System.out.println(Thread.currentThread().getName()+"\t�Լ�����:"+lock2+"\t���Ի�ã�"+lock1);
            }
        }
    }
}
public class DeadLockDemo
{
    /*
    * �����������������������ϵ��߳�ִ��ʱ����Դ���л���ȴ������������������涼�޷��ƽ���ȥ*/
    public static void main(String[] args) {
        String lock1="lock1";
        String lock2="lock2";
        new Thread(new HoldLockThread(lock1,lock2),"�߳�1").start();
        new Thread(new HoldLockThread(lock2,lock1),"�߳�2").start();
    }
}

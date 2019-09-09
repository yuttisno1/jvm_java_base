package yutt.mianshiti;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception{
        //closeDoor();
        CountDownLatch count=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国破灭");
                count.countDown();
            },CountDownLaEnum.forEach_CountDownLaEnum(i).getRetMessage()).start();
        }
        count.await();
        System.out.println(Thread.currentThread().getName()+"\t 6国统一 ");

    }

    public static void closeDoor() throws InterruptedException {
        CountDownLatch count=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
            count.countDown();
                },String.valueOf(i)).start();
        }
        count.await();
        System.out.println(Thread.currentThread().getName()+"\t 关门 ");
    }
}

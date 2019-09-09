package yutt.mianshiti;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
    MyCache my=new MyCache();
        for (int i = 0; i <=5 ; i++) {
            final int num=i;
            new Thread(()->{
                my.put(num+"",num);
            },String.valueOf(i)).start();
        }
        for (int i = 0; i <=5 ; i++) {
            final int num=i;
            new Thread(()->{
                my.get(num+"");
            },String.valueOf(i)).start();
        }
    }
}
//��Դ��
class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();
    //private Lock lock=new ReentrantLock();
    private ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();
    public void put(String key,Object value)
    {
        rwlock.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t д�뿪ʼ"+key);
            try{TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e){e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t д�����"+key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            rwlock.writeLock().unlock();
        }

    }
    public void get(String key)
    {
        rwlock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t ��ȡ��ʼ");
            try{
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e){e.printStackTrace();}
            Object result=map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t ��ȡ���"+result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            rwlock.readLock().unlock();
        }

    }
}

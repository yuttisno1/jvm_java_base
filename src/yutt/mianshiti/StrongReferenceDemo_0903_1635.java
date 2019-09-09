package yutt.mianshiti;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/*强软弱虚四种引用*/
public class StrongReferenceDemo_0903_1635 {
    public static void main(String[] args) throws InterruptedException {
        //qiangyinyong();//强引用
   //    ruanyinyong(); //软引用
    //    weakReferenceM();//弱引用
        //引用队列
     //   referenceQueue();
        //虚引用
        phantomReference();
    }

    //软引用 够用保留，不够回收
    public static void ruanyinyong() {
        Object obj1=new Object();//强引用
        SoftReference<Object> softReference=new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());
        obj1=null;
        //第一种
//        //内存够用时，软引用会输出
//        System.gc();
//        System.out.println(obj1);
//        System.out.println(softReference.get());

        //第二种
        //修改配置 -Xms5m -Xmx5m -XX:+PrintGCDetails
        try{
            byte[] bytes=new byte[30*1024*1024];
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        finally {
                   System.out.println(obj1);
                   System.out.println(softReference.get());
        }
    }
    //强引用
    public static void qiangyinyong() {
        Object obj1=new Object();//默认为强引用
        Object obj2=obj1;//obj2引用赋值
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);
        System.out.println(obj1);
    }
//弱引用
    public static void weakReferenceM() {
        Object obj1=new Object();
        WeakReference<Object> weakReference=new WeakReference<>(obj1);
        System.out.println(obj1);
        System.out.println(weakReference.get());
        obj1 = null;//置空
        System.gc();
        System.out.println("******************");
        System.out.println(obj1);
        System.out.println(weakReference.get());
    }

    //引用队列
    private static void referenceQueue() throws InterruptedException {
        Object obj1=new Object();
        ReferenceQueue<Object> referenceQueue=new ReferenceQueue();
        WeakReference<Object> weakReference=new WeakReference<>(obj1,referenceQueue);
        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("------------------------");
        obj1 = null;//置空
        System.gc();
        Thread.sleep(500);
        System.out.println(obj1);//因为obj1 = null;，所以返回null
        System.out.println(weakReference.get());//弱引用，只要回收就为null
        System.out.println(referenceQueue.poll());//如果GC回收的化，放到引用队列中 java.lang.ref.WeakReference@14ae5a5
    }


    //虚引用  PhantomReference
    private static void phantomReference() throws InterruptedException {
        Object obj1=new Object();
        ReferenceQueue<Object> referenceQueue=new ReferenceQueue();
        PhantomReference<Object> phantomReference=new PhantomReference<>(obj1,referenceQueue);
        System.out.println(obj1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("------------------------");
        obj1 = null;//置空
        System.gc();
        Thread.sleep(500);
        System.out.println(obj1);//因为obj1 = null;，所以返回null
        System.out.println(phantomReference.get());//弱引用，只要回收就为null
        System.out.println(referenceQueue.poll());//如果GC回收的化，放到引用队列中 java.lang.ref.WeakReference@14ae5a5
    }
}

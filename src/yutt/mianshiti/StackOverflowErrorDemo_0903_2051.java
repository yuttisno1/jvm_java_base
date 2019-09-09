package yutt.mianshiti;

//import java.net.;//net.sf.cglib.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StackOverflowErrorDemo_0903_2051 {
    public static void main(String[] args) {
         // StackOverflowError();//StackoverFlowError 栈溢出  自身调用自身方法
        javaheapspace();//OutofMemeoryError:java heap space 堆溢出
       // gCoverheadlimitexceeded();
       // directBufferMemory();
      //  metaspacesize(args);
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
    }
//查看最大堆内存大小
    private static void directBufferMemory() {
        System.out.println("配置的maxDirectMemory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        ByteBuffer bytes=ByteBuffer.allocateDirect(6*1024*1024);
    }


    //栈溢出
    private static void StackOverflowError()
    {
        //Exception in thread "main" java.lang.StackOverflowError
        /*
        * 此问题为异常还是错误，为错误
        * */
        StackOverflowError();
    }
    //OutofMemeoryError:java heap space 堆溢出
    //-Xms10m -Xmx10m
    public static void javaheapspace()
    {
        String str="hello";
        while(true)
        {
            str+=str+new Random().nextInt(111111111)+new Random().nextInt(22222222);
            str.intern();
        }

    }
    //java.lang.OutOfMemoryError:GC overhead limit exceeded
    //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
    //[Full GC (Ergonomics) java.lang.OutOfMemoryError: GC overhead limit exceeded
    private static void gCoverheadlimitexceeded() {
        int i=0;
        List<String> list=new ArrayList<>();
       try{
           while(true)
           {
               list.add(String.valueOf(++i).intern());
           }
       }
       catch (Throwable e)
       {
          System.out.println("i="+i);
           e.printStackTrace();
       }
    }
    //-XX:MetaspaceSize
    //-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
    private static void metaspacesize(String[] args)
    {
        int i=0;//模拟计数器
        try {
            while (true)
            {
                i++;
                //Enhancer
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor()
                {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        }
        catch (Throwable e)
        {
            System.out.println("多少次后发生异常："+i);
            e.printStackTrace();
        }
    }
static  class OOMTest{}
}

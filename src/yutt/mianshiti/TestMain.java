package yutt.mianshiti;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class TestMain {
    public static void main(String[] args)
    {
        Singleton3 s1=Singleton3.getInstance();
        Singleton3 s2=Singleton3.getInstance();
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);
        Callable<Singleton3> c=new Callable<Singleton3>() {
            @Override
            public Singleton3 call() throws Exception {
                return Singleton3.getInstance();
            }
        };
        ExecutorService es= Executors.newFixedThreadPool(2);
        Future<Singleton3> f1=es.submit(c);
        Future<Singleton3> f2=es.submit(c);
//        Singleton3 s5=f1.get();
//        Singleton3 s6=f2.get();


    }
}

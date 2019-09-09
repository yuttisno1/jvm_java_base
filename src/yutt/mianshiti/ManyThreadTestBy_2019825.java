package yutt.mianshiti;

import java.util.concurrent.atomic.AtomicInteger;

public class ManyThreadTestBy_2019825 {
    static  AtomicInteger ato=new AtomicInteger();
    public static void main(String[] args) {
        Thread th=new Thread();
    }
    public static void  thread()
    {
        for (int j = 0; j < 1000; j++) {
            ato.getAndIncrement();
        }
    }
}

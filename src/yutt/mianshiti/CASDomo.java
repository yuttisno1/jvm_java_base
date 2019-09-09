package yutt.mianshiti;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDomo {

    public static void main(String[] args) {
        AtomicInteger ato =new AtomicInteger(5);
        ato.getAndIncrement();
        System.out.println(ato.compareAndSet(5,2019)+"\t 结果是:"+ato.get());
        System.out.println(ato.compareAndSet(5,1000)+"\t 结果是:"+ato.get());
    }
}

package yutt.mianshiti;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> bq=new ArrayBlockingQueue<>(3);
        //1
//        System.out.println(bq.add("a"));
//        System.out.println(bq.add("b"));
//        System.out.println(bq.add("c"));
//        System.out.println(bq.element()+"**********");
//        System.out.println(bq.remove());
//        System.out.println(bq.remove());
//        System.out.println(bq.remove());
        //2
        System.out.println(bq.offer("a"));
        System.out.println(bq.offer("b"));
        System.out.println(bq.offer("c"));
        System.out.println(bq.offer("d"));
        System.out.println(bq.peek());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        //3
//        bq.put("a");
//        bq.put("b");
//        bq.put("c");
////        bq.put("d");
//
//        System.out.println(bq.take());
//        System.out.println(bq.take());
//        System.out.println(bq.take());
////        System.out.println(bq.take());
//        System.out.println(bq.offer("a",1, TimeUnit.SECONDS));
//        System.out.println(bq.offer("b",1, TimeUnit.SECONDS));
//        System.out.println(bq.offer("c",1, TimeUnit.SECONDS));
//        System.out.println(bq.offer("d",1, TimeUnit.SECONDS));






    }
}

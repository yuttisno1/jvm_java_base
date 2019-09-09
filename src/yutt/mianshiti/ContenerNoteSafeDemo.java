package yutt.mianshiti;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class ContenerNoteSafeDemo {
    public static void main(String[] args) {
        //ArrayList 线程不安全
     //   ArrayListNotSafe();
        //java.util.ConcurrentModificationException
        // Map<String,String> map =new HashMap<>();
        //ConcurrentHashMap 线程安全
         Map<String,String> map =new ConcurrentHashMap<>();
        for (int i = 0; i <=30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }


    }

    public static void ArrayListNotSafe() {
        //方法一
//        List<String> list= Arrays.asList("a","b","c");
//        list.forEach(System.out::println);
        //方法二
//        List<String> list=new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        for(String element:list)
//        {
//            System.out.println(element);
//        }
        //ArrayList线程不安全
        // java.util.ConcurrentModificationException 并发类的错误 Comcurrent并发修改异常
        //at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
        //   List<String> list=new ArrayList<>();
        //Vector 并发性弱
        //    List<String> list=new Vector<>();
        //Collections.synchronizedList(new ArrayList<>()); 集合工具类
        //   List<String> list=Collections.synchronizedList(new ArrayList<>());
        //CopyOnWriteArrayList
        List<String> list=new CopyOnWriteArrayList<>();
        for (int i = 1; i <=30; i++) {
              new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
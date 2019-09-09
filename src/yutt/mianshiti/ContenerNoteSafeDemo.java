package yutt.mianshiti;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class ContenerNoteSafeDemo {
    public static void main(String[] args) {
        //ArrayList �̲߳���ȫ
     //   ArrayListNotSafe();
        //java.util.ConcurrentModificationException
        // Map<String,String> map =new HashMap<>();
        //ConcurrentHashMap �̰߳�ȫ
         Map<String,String> map =new ConcurrentHashMap<>();
        for (int i = 0; i <=30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }


    }

    public static void ArrayListNotSafe() {
        //����һ
//        List<String> list= Arrays.asList("a","b","c");
//        list.forEach(System.out::println);
        //������
//        List<String> list=new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        for(String element:list)
//        {
//            System.out.println(element);
//        }
        //ArrayList�̲߳���ȫ
        // java.util.ConcurrentModificationException ������Ĵ��� Comcurrent�����޸��쳣
        //at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
        //   List<String> list=new ArrayList<>();
        //Vector ��������
        //    List<String> list=new Vector<>();
        //Collections.synchronizedList(new ArrayList<>()); ���Ϲ�����
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
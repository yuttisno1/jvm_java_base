package yutt.mianshiti;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo_0903_1931 {
    public static void main(String[] args) {
        myHashmap();
        System.out.println("---------------------------------");
        myWeakHashMap();
    }
//WeakHashMap
    private static void myWeakHashMap() {
        WeakHashMap<Integer,String> map=new WeakHashMap<>();
        Integer key=new Integer(2);
        String value="HashMap";

        map.put(key,value);
        System.out.println(map);

        key=null;
        System.out.println(map);
//底层为Node节点的kv键值对
        System.gc();
        System.out.println(map+"***"+map.size());
    }

    protected static void myHashmap(){
        HashMap<Integer,String> map=new HashMap<>();
        Integer key=new Integer(1);
        String value="HashMap";

        map.put(key,value);
        System.out.println(map);

        key=null;
        System.out.println(map);
//底层为Node节点的kv键值对
        System.gc();
        System.out.println(map+"***"+map.size());

    }
}

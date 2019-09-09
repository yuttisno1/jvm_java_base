package yutt.mianshiti;

public class GCRootDemo20190902_1042 {
    private byte[] byteArray=new byte[100*1024*1024];
    public static void main(String[] args) {
        m1();
    }
    public static void m1()
    {
        GCRootDemo20190902_1042 t1=new GCRootDemo20190902_1042();
        System.gc();
        System.out.println("第一次GC完成");
    }
}

package yutt.mianshiti;

public class Examp2 {
    static int s;int i;int j;
    {
        int i=1;
        i++;
        j++;
        s++;
    }
    public void test(int j)
    {
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        Examp2 e1=new Examp2();
        Examp2 e2=new Examp2();
        e1.test(10);
        e1.test(20);
        e2.test(30);
        System.out.println(e1.i+","+e1.j+","+e1.s);
        System.out.println(e2.i+","+e2.j+","+e2.s);
    }
}

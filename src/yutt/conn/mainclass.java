package yutt.conn;

public class mainclass {
    public static void main(String[] args)
    {
        int i=1;
        System.out.println("i1="+i);

        i=i++;
        System.out.println("i2="+i);
        int j=i++;
        System.out.println("i3="+i);
        System.out.println("j3="+j);
        int k=i+ ++i *i++ ;
        System.out.println("i4="+i);
        System.out.println("j4="+j);
        System.out.println("k4="+k);

    }
}

package yutt.mianshiti;

public class Taijie {
    public static void main(String[] args) {
        Taijie r=new Taijie();
        System.out.println(r.f(22));//28657
    }
    public int f(int x)
    {

        if(x<1)
        {
            throw new IllegalArgumentException("x不能小于1");
        }
        int t=0;
        if(x==1 || x==2)
        {
            return x;
        }
     //   return f(x-2)+f(x-1);
        int one=2;
        int two=1;
        int sum=0;
            for(int i=3;i<=x;i++)
            {
               sum=two+one;
               two=one;
               one=sum;
            }
        return sum;
    }
}

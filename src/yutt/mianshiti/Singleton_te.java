package yutt.mianshiti;
//单例模式的DCL代码
public class Singleton_te {
    private static Singleton_te ourInstance=null;
    private Singleton_te()
    {
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法");
    }

    public static Singleton_te getInstance() {
        //双锁机制 DCL double check lock
        if (ourInstance == null) {
            synchronized (Singleton_te.class)
            {
                if (ourInstance == null) {
                    ourInstance = new Singleton_te();
                }
            }
        }
        return ourInstance;
    }

    public static void main(String[] args) {
        for(int i=1;i<=20;i++)
        {
            new Thread(()->{
                Singleton_te.getInstance();
            },String.valueOf(i)).start();
        }
    }
}

package yutt.mianshiti;

public class Singleton3 {
    private static Singleton3 ourInstance;

    public static Singleton3 getInstance() {
        if(ourInstance==null) {
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            ourInstance = new Singleton3();
        }
        return ourInstance;
    }

    private Singleton3() {
    }
}

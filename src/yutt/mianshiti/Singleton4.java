package yutt.mianshiti;

public class Singleton4 {
    private static Singleton4 ourInstance;

    public static Singleton4 getInstance() {
        synchronized (Singleton4.class) {
            if (ourInstance == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ourInstance = new Singleton4();
            }
            return ourInstance;
        }
    }
    private Singleton4() {
    }
}

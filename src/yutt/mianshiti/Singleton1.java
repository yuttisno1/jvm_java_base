package yutt.mianshiti;
/*
饿汉式
构造器私有化
自行创建，内部创构建，静态变量
向外提供实例
强调这是一个单例，用final修改
final修饰的词，习惯大写
*/
public class Singleton1 {
    public static  final Singleton1 INSTANCE=new Singleton1();
    private Singleton1(){}
}

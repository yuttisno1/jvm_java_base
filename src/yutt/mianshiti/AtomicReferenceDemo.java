package yutt.mianshiti;

import java.util.concurrent.atomic.AtomicReference;
//import jdk.nashorn.internal.objects.annotations.Getter;
//import lombok.AllArgsConstructor;

//@Getter
//@ToString
//@AllArgsConstructor
class User
{
    String name;
    int age;
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //    public User()
//    {
//        this.age=getAge();
//    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zhang =new User("zs",22);
        User li =new User("li",22);
        User wang =new User("ww",22);
        AtomicReference<User> atomicReference=new AtomicReference<>();
        atomicReference.set(zhang);
        System.out.println( atomicReference.compareAndSet(zhang,li)+"\t "+atomicReference.get().toString());
        System.out.println( atomicReference.compareAndSet(zhang,li)+"\t "+atomicReference.get().toString());

    }
}


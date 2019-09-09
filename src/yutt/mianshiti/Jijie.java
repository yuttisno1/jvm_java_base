package yutt.mianshiti;

public enum Jijie
{
    chun(1,"春天","花开了"),xia(2,"夏天","在一片绿色的海洋上"),qiu(3,"秋天","叶子黄了"),dong(4,"冬天","白雪皑皑");
     Integer key;
     String value;
    String value2;

    public String getValue2() {
        return value2;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    Jijie(Integer key, String value, String value2) {
        this.key = key;
        this.value = value;
        this.value2 = value2;
    }
    public static Jijie Jijie_Enum(int code)
    {
        Jijie[] jijies=Jijie.values();
        for (Jijie ji:jijies)
        {
            if (code==ji.getKey()) {
                return ji;
            }
        }
        return null;
    }
}

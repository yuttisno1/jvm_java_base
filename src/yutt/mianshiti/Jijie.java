package yutt.mianshiti;

public enum Jijie
{
    chun(1,"����","������"),xia(2,"����","��һƬ��ɫ�ĺ�����"),qiu(3,"����","Ҷ�ӻ���"),dong(4,"����","��ѩ����");
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

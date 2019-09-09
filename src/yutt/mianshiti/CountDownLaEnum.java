package yutt.mianshiti;


public enum CountDownLaEnum {
  //  ��;
    ONE(1,"��"),TWO(2,"��"),THREE(3,"��"),FOUR(4,"��"),FIVE(5,"��"),SIX(6,"κ");
    private Integer retCode;
    private String retMessage;

    CountDownLaEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }
    public static CountDownLaEnum  forEach_CountDownLaEnum(int index)
    {
        CountDownLaEnum[] array= CountDownLaEnum.values();
        for (CountDownLaEnum element : array)
        {
            if (index == element.getRetCode()) {
                return element;
            }
        }
        return null;
    }
}

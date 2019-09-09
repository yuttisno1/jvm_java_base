package yutt.mianshiti;


public enum CountDownLaEnum {
  //  ÇØ;
    ONE(1,"Æë"),TWO(2,"³þ"),THREE(3,"Ñà"),FOUR(4,"º«"),FIVE(5,"ÕÔ"),SIX(6,"Îº");
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

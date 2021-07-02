package cn.lngex.common.constant;

public enum DataDictionary {
    BRONZE(1L,"青铜"),
    SILVER(2L,"白银"),
    GOLD(3L,"黄金"),
    PLATINUM(4L,"铂金");

    private Long id;
    private String name;
    private DataDictionary(Long id,String name){
        this.id=id;
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public DataDictionary setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DataDictionary setName(String name) {
        this.name = name;
        return this;
    }

    public static DataDictionary getDataDictionary(Long id){
        DataDictionary[] values = DataDictionary.values();
        for (DataDictionary value:values) {
            if (value.getId() == id){
                return value;
            }
        }
        return null;
    }
}

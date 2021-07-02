package cn.lngex.search.doc.vo;

public class AggVo {
    private Long count;
    private String name;

    @Override
    public String toString() {
        return "AggVo{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getCount() {
        return count;
    }

    public AggVo setCount(Long count) {
        this.count = count;
        return this;
    }

    public String getName() {
        return name;
    }

    public AggVo setName(String name) {
        this.name = name;
        return this;
    }
}

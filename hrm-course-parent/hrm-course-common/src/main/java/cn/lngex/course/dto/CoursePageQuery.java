package cn.lngex.course.dto;


public class CoursePageQuery{
    private String courseTypeId;
    private Float priceMin;
    private Float priceMax;
    private String courseTypeName;
    private String tenantName;
    private String sortField;
    private String sortType;
    //关键字
    private String keyword;
    //有公共属性-分页
    private Integer page = 1; //当前页
    private Integer rows = 10; //每页显示多少条

    @Override
    public String toString() {
        return "CoursePageQuery{" +
                "courseTypeId='" + courseTypeId + '\'' +
                ", priceMin='" + priceMin + '\'' +
                ", priceMax='" + priceMax + '\'' +
                ", courseTypeName='" + courseTypeName + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", sortField='" + sortField + '\'' +
                ", sortType='" + sortType + '\'' +
                ", keyword='" + keyword + '\'' +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }

    public String getKeyword() {
        return keyword;
    }

    public CoursePageQuery setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public CoursePageQuery setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getRows() {
        return rows;
    }

    public CoursePageQuery setRows(Integer rows) {
        this.rows = rows;
        return this;
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public CoursePageQuery setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
        return this;
    }

    public Float getPriceMin() {
        return priceMin;
    }

    public CoursePageQuery setPriceMin(Float priceMin) {
        this.priceMin = priceMin;
        return this;
    }

    public Float getPriceMax() {
        return priceMax;
    }

    public CoursePageQuery setPriceMax(Float priceMax) {
        this.priceMax = priceMax;
        return this;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public CoursePageQuery setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
        return this;
    }

    public String getTenantName() {
        return tenantName;
    }

    public CoursePageQuery setTenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    public String getSortField() {
        return sortField;
    }

    public CoursePageQuery setSortField(String sortField) {
        this.sortField = sortField;
        return this;
    }

    public String getSortType() {
        return sortType;
    }

    public CoursePageQuery setSortType(String sortType) {
        this.sortType = sortType;
        return this;
    }
}

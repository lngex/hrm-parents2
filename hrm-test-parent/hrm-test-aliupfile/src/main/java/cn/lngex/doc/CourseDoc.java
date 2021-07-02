package cn.lngex.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "hrm" , type = "course")
public class CourseDoc {
    //PUT  /hrmtest/coursetest/1
    @Id //文档对象的ID就是该字段的值
    private Long id;
    //课程名称
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String name;
    //价格
    @Field(type = FieldType.Float)
    private Float price;
    //原价
    @Field(type = FieldType.Float)
    private Float priceOld;
    //适用人群
    @Field(type = FieldType.Keyword,index = false)
    private String forUser;
    //课程类型
    @Field(type = FieldType.Long)
    private Long courseTypeId;
    //等级名
    @Field(type = FieldType.Keyword)
    private String gradeName;
    //机构
    @Field(type = FieldType.Keyword)
    private String tenantName;
    @Field(type = FieldType.Long)
    private Long tenantId;
    //图片
    @Field(type = FieldType.Keyword,index = false)
    private String pic;
    //销量
    @Field(type = FieldType.Integer)
    private Integer saleCount = 0;
    //浏览量
    @Field(type = FieldType.Integer)
    private Integer viewCount = 0;
    //评论数
    @Field(type = FieldType.Integer)
    private Integer commentCount = 0;
    //是否收费
    @Field(type = FieldType.Integer)
    private Integer charge;
    //上线时间
    @Field(type = FieldType.Date)
    private Date onlineTime;

    public CourseDoc(Long id, String name, Float price, Float priceOld, String forUser, Long courseTypeId, String gradeName, String tenantName, Long tenantId, String pic, Integer saleCount, Integer viewCount, Integer commentCount, Integer charge, Date onlineTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.priceOld = priceOld;
        this.forUser = forUser;
        this.courseTypeId = courseTypeId;
        this.gradeName = gradeName;
        this.tenantName = tenantName;
        this.tenantId = tenantId;
        this.pic = pic;
        this.saleCount = saleCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.charge = charge;
        this.onlineTime = onlineTime;
    }

    public CourseDoc() {
    }

    @Override
    public String toString() {
        return "CourseDoc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", priceOld=" + priceOld +
                ", forUser='" + forUser + '\'' +
                ", courseTypeId=" + courseTypeId +
                ", gradeName='" + gradeName + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", tenantId=" + tenantId +
                ", pic='" + pic + '\'' +
                ", saleCount=" + saleCount +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                ", charge=" + charge +
                ", onlineTime=" + onlineTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public CourseDoc setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseDoc setName(String name) {
        this.name = name;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public CourseDoc setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Float getPriceOld() {
        return priceOld;
    }

    public CourseDoc setPriceOld(Float priceOld) {
        this.priceOld = priceOld;
        return this;
    }

    public String getForUser() {
        return forUser;
    }

    public CourseDoc setForUser(String forUser) {
        this.forUser = forUser;
        return this;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public CourseDoc setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
        return this;
    }

    public String getGradeName() {
        return gradeName;
    }

    public CourseDoc setGradeName(String gradeName) {
        this.gradeName = gradeName;
        return this;
    }

    public String getTenantName() {
        return tenantName;
    }

    public CourseDoc setTenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public CourseDoc setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getPic() {
        return pic;
    }

    public CourseDoc setPic(String pic) {
        this.pic = pic;
        return this;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public CourseDoc setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
        return this;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public CourseDoc setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public CourseDoc setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public Integer getCharge() {
        return charge;
    }

    public CourseDoc setCharge(Integer charge) {
        this.charge = charge;
        return this;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public CourseDoc setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
        return this;
    }
}


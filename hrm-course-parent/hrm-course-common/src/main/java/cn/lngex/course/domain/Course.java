package cn.lngex.course.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-20
 */
@TableName("t_course")
public class Course extends Model<Course> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 适用人群
     */
    @TableField("for_user")
    private String forUser;
    /**
     * 课程分类
     */
    @TableField("course_type_id")
    private Long courseTypeId;
    @TableField("grade_name")
    private String gradeName;
    /**
     * 课程等级
     */
    @TableField("grade_id")
    private Long gradeId;
    /**
     * 课程状态，下线：0 ， 上线：1
     */
    private Integer status;
    /**
     * 教育机构
     */
    @TableField("tenant_id")
    private Long tenantId;
    @TableField("tenant_name")
    private String tenantName;
    /**
     * 添加课程的后台用户的ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 添加课程的后台用户
     */
    @TableField("user_name")
    private String userName;
    /**
     * 课程的开课时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 课程的节课时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 封面
     */
    private String pic;
    @TableField("sale_count")
    private Integer saleCount;
    @TableField("view_count")
    private Integer viewCount;
    /**
     * 评论数
     */
    @TableField("comment_count")
    private Integer commentCount;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    public String getForUser() {
        return forUser;
    }

    public Course setForUser(String forUser) {
        this.forUser = forUser;
        return this;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public Course setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
        return this;
    }

    public String getGradeName() {
        return gradeName;
    }

    public Course setGradeName(String gradeName) {
        this.gradeName = gradeName;
        return this;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public Course setGradeId(Long gradeId) {
        this.gradeId = gradeId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Course setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public Course setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getTenantName() {
        return tenantName;
    }

    public Course setTenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Course setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Course setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Course setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Course setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getPic() {
        return pic;
    }

    public Course setPic(String pic) {
        this.pic = pic;
        return this;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public Course setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
        return this;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Course setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Course setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Course{" +
        ", id=" + id +
        ", name=" + name +
        ", forUser=" + forUser +
        ", courseTypeId=" + courseTypeId +
        ", gradeName=" + gradeName +
        ", gradeId=" + gradeId +
        ", status=" + status +
        ", tenantId=" + tenantId +
        ", tenantName=" + tenantName +
        ", userId=" + userId +
        ", userName=" + userName +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", pic=" + pic +
        ", saleCount=" + saleCount +
        ", viewCount=" + viewCount +
        ", commentCount=" + commentCount +
        "}";
    }
}

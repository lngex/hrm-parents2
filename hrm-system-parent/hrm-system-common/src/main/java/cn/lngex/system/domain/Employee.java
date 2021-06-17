package cn.lngex.system.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
@TableName("t_employee")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 员工用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 电话
     */
    private String tel;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    @TableField("input_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date inputTime = new Date();
    /**
     * 状态：0正常，1锁定，2注销
     */
    private Integer state;
    /**
     * 部门id
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * 所属租户
     */
    @TableField("tenant_id")
    private Long tenantId;
    /**
     * 员工类型 ， 1平台普通员工 ，2平台客服人员，3平台管理员，4机构员工，5,机构管理员或其他
     */
    private Integer type;
    private String salt;
    @TableField("login_id")
    private Long loginId;

    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Employee setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public Employee setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public Employee setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public Employee setInputTime(Date inputTime) {
        this.inputTime = inputTime;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public Employee setState(Integer state) {
        this.state = state;
        return this;
    }

    public Long getDeptId() {
        return deptId;
    }

    public Employee setDeptId(Long deptId) {
        this.deptId = deptId;
        return this;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public Employee setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Employee setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public Employee setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public Long getLoginId() {
        return loginId;
    }

    public Employee setLoginId(Long loginId) {
        this.loginId = loginId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Employee{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", realName=" + realName +
        ", tel=" + tel +
        ", email=" + email +
        ", inputTime=" + inputTime +
        ", state=" + state +
        ", deptId=" + deptId +
        ", tenantId=" + tenantId +
        ", type=" + type +
        ", salt=" + salt +
        ", loginId=" + loginId +
        "}";
    }
}

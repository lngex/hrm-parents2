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
@TableName("t_tenant")
public class Tenant extends Model<Tenant> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("tenant_type_id")
    private Long tenantTypeId;
    @TableField("company_name")
    private String companyName;
    @TableField("company_num")
    private String companyNum;
    @TableField("register_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date registerTime = new Date();
    /**
     * 0待审核，1 审核通过 ， 2审核失败
     */
    private Integer state;
    private String address;
    private String logo;
    @TableField("admin_id")
    private Long adminId;

    @TableField(exist = false)
    private TenantType type;

    @TableField(exist = false)
    private Employee admin;

    public Tenant() {
    }

    public Long getId() {
        return id;
    }

    public Tenant setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTenantTypeId() {
        return tenantTypeId;
    }

    public Tenant setTenantTypeId(Long tenantTypeId) {
        this.tenantTypeId = tenantTypeId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Tenant setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public Tenant setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
        return this;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public Tenant setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public Tenant setState(Integer state) {
        this.state = state;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Tenant setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public Tenant setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public Long getAdminId() {
        return adminId;
    }

    public Tenant setAdminId(Long adminId) {
        this.adminId = adminId;
        return this;
    }

    public TenantType getType() {
        return type;
    }

    public Tenant setType(TenantType type) {
        this.type = type;
        return this;
    }

    public Employee getAdmin() {
        return admin;
    }

    public Tenant setAdmin(Employee admin) {
        this.admin = admin;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Tenant{" +
        ", id=" + id +
        ", tenantTypeId=" + tenantTypeId +
        ", companyName=" + companyName +
        ", companyNum=" + companyNum +
        ", registerTime=" + registerTime +
        ", state=" + state +
        ", address=" + address +
        ", logo=" + logo +
        ", adminId=" + adminId +
        "}";
    }
}

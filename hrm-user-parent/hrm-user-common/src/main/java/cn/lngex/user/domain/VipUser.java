package cn.lngex.user.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员登录账号
 * </p>
 *
 * @author ï¿½ï¿½Ä³
 * @since 2021-06-25
 */
@TableName("t_vip_user")
public class VipUser extends Model<VipUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("create_time")
    private Long createTime;
    @TableField("update_time")
    private Long updateTime;
    /**
     * 三方登录名
     */
    @TableField("third_uid")
    private String thirdUid;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 用户状态
     */
    @TableField("bit_state")
    private Long bitState;
    /**
     * 安全级别
     */
    @TableField("sec_level")
    private Integer secLevel;
    @TableField("login_id")
    private Long loginId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public VipUser setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public VipUser setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public VipUser setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getThirdUid() {
        return thirdUid;
    }

    public VipUser setThirdUid(String thirdUid) {
        this.thirdUid = thirdUid;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public VipUser setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public VipUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public VipUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public VipUser setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public VipUser setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public VipUser setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public Long getBitState() {
        return bitState;
    }

    public VipUser setBitState(Long bitState) {
        this.bitState = bitState;
        return this;
    }

    public Integer getSecLevel() {
        return secLevel;
    }

    public VipUser setSecLevel(Integer secLevel) {
        this.secLevel = secLevel;
        return this;
    }

    public Long getLoginId() {
        return loginId;
    }

    public VipUser setLoginId(Long loginId) {
        this.loginId = loginId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "VipUser{" +
        ", id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", thirdUid=" + thirdUid +
        ", phone=" + phone +
        ", email=" + email +
        ", password=" + password +
        ", avatar=" + avatar +
        ", salt=" + salt +
        ", nickName=" + nickName +
        ", bitState=" + bitState +
        ", secLevel=" + secLevel +
        ", loginId=" + loginId +
        "}";
    }
}

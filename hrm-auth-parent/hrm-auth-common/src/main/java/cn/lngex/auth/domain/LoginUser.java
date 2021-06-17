package cn.lngex.auth.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ÁÎÄ³
 * @since 2021-06-17
 */
@TableName("t_login_user")
public class LoginUser extends Model<LoginUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Integer type;

    public Long getId() {
        return id;
    }

    public LoginUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public LoginUser setType(Integer type) {
        this.type = type;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", type=" + type +
        "}";
    }
}

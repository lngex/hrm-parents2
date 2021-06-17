package cn.lngex.auth.domain;

import java.util.Date;
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
@TableName("t_user_meal")
public class UserMeal extends Model<UserMeal> {

    private static final long serialVersionUID = 1L;

    @TableField("meal_id")
    private Long mealId;
    @TableField("login_id")
    private Long loginId;
    /**
     * 该机构的该套餐到期时间
     */
    @TableField("expire_date")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date expireDate = new Date();
    /**
     * 状态,是否过期 0 未支付，1已经购买，2过期
     */
    private Integer state;

    public Long getMealId() {
        return mealId;
    }

    public UserMeal setMealId(Long mealId) {
        this.mealId = mealId;
        return this;
    }

    public Long getLoginId() {
        return loginId;
    }

    public UserMeal setLoginId(Long loginId) {
        this.loginId = loginId;
        return this;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public UserMeal setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public UserMeal setState(Integer state) {
        this.state = state;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
        ", mealId=" + mealId +
        ", loginId=" + loginId +
        ", expireDate=" + expireDate +
        ", state=" + state +
        "}";
    }
}

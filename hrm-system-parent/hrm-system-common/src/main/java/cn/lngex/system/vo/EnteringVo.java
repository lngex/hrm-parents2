package cn.lngex.system.vo;

import cn.lngex.system.domain.Employee;
import cn.lngex.system.domain.Tenant;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EnteringVo implements Serializable {

    /**
     * 机构对象
     */
    @Valid
    private Tenant tenant;

    /**
     * 员工对象
     */
    @Valid
    private Employee employee;

    /**
     * mealId
     */
    @NotNull(message = "mealId不能为空")
    private Long mealId;

    @Override
    public String toString() {
        return "EnteringVo{" +
                "tenant=" + tenant +
                ", employee=" + employee +
                ", mealId=" + mealId +
                '}';
    }

    public Tenant getTenant() {
        return tenant;
    }

    public EnteringVo setTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public Employee getEmployee() {
        return employee;
    }

    public EnteringVo setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public Long getMealId() {
        return mealId;
    }

    public EnteringVo setMealId(Long mealId) {
        this.mealId = mealId;
        return this;
    }

    public void setEmployee() {
    }
}

package cn.lngex.auth.domain;

import java.io.Serializable;

public class EmpTen implements Serializable {
    private Long tenantId;
    private String tenantName;
    private Long userId;
    private String userName;

    @Override
    public String toString() {
        return "EmpTen{" +
                "tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Long getTenantId() {
        return tenantId;
    }

    public EmpTen setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getTenantName() {
        return tenantName;
    }

    public EmpTen setTenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public EmpTen setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public EmpTen setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}

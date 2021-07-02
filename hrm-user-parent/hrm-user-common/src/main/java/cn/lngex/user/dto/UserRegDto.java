package cn.lngex.user.dto;

import javax.validation.constraints.NotBlank;

public class UserRegDto {
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "短信验证吗不能为空")
    private String smsCode;

    @Override
    public String toString() {
        return "UserRegDto{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public UserRegDto setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public UserRegDto setSmsCode(String smsCode) {
        this.smsCode = smsCode;
        return this;
    }

    public UserRegDto() {
    }

    public UserRegDto(String mobile, String password, String smsCode) {
        this.mobile = mobile;
        this.password = password;
        this.smsCode = smsCode;
    }
}

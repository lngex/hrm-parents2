package cn.lngex.auth.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginDto {
    @NotBlank(message = "用户字为空")
    private String username;
    @NotBlank(message = "密码字为空")
    private String password;
    @NotNull(message = "非法参数异常")
    private Integer type;

    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public LoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public LoginDto setType(Integer type) {
        this.type = type;
        return this;
    }
}

package cn.lngex.user.dto;

public class SmsCodeDeto {
    private String mobile;
    private String imageCode;
    private String imageCodeKey;

    @Override
    public String toString() {
        return "SmsCodeDeto{" +
                "mobile='" + mobile + '\'' +
                ", imageCode='" + imageCode + '\'' +
                ", imageCodeKey='" + imageCodeKey + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public SmsCodeDeto setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getImageCode() {
        return imageCode;
    }

    public SmsCodeDeto setImageCode(String imageCode) {
        this.imageCode = imageCode;
        return this;
    }

    public String getImageCodeKey() {
        return imageCodeKey;
    }

    public SmsCodeDeto setImageCodeKey(String imageCodeKey) {
        this.imageCodeKey = imageCodeKey;
        return this;
    }

    public SmsCodeDeto(String mobile, String imageCode, String imageCodeKey) {
        this.mobile = mobile;
        this.imageCode = imageCode;
        this.imageCodeKey = imageCodeKey;
    }

    public SmsCodeDeto() {
    }
}

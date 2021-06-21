package cn.lngex.oss.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file.alicloud")
@Component
public class ParameterConfig {
    private String bucketName;
    private String accessKey;
    private String secretKey;
    private String endpoint;

    public ParameterConfig() {
    }

    public ParameterConfig(String bucketName, String accessKey, String secretKey, String endpoint) {
        this.bucketName = bucketName;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public ParameterConfig setBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public ParameterConfig setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public ParameterConfig setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public ParameterConfig setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }
}

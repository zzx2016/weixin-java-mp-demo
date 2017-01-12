package com.dreamer.invite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QiNiuConfig {
	
	@Value("#{qiniuProperties.ACCESS_KEY}")
	private String accessKey;
	
	@Value("#{qiniuProperties.SECRET_KEY}")
	private String secretKey;
	
	@Value("#{qiniuProperties.BUCKET_NAME}")
	private String bucketName;
	
	@Value("#{qiniuProperties.DOMAIN}")
	private String domain;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "QiNiuConfig [accessKey=" + accessKey + ", secretKey=" + secretKey + ", bucketName=" + bucketName
				+ ", domain=" + domain + "]";
	}
	
	
	

}

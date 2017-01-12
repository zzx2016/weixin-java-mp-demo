package com.dreamer.invite.qiniu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dreamer.invite.config.QiNiuConfig;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

@Component
public class QiNiuSingleton {
	
	@Autowired
	QiNiuConfig qiniuConfig;
	
	private static Auth auth = null;// 授权，所有操作的前提
	
	private static Configuration config = null;//配置类对象

	private static UploadManager uploadManager = null;// 文件上传的对象

	private static BucketManager bucketManager = null;// 操作文件的对象
	
	public BucketManager getBucketManager(){
		if(bucketManager==null){
			bucketManager = new BucketManager(getAuth(), getConfiguration());
		}
		return bucketManager;
	}
	
	public UploadManager getUploadManager(){
		if(uploadManager==null){
			uploadManager = new UploadManager(getConfiguration());
		}
		return uploadManager;
	}
	
	private Configuration getConfiguration(){
		if(config==null){
			Zone z = Zone.zone0();
			return new Configuration(z);
		}
		return config;
	}
	
	public Auth getAuth(){
		if(auth==null){
			return Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
		}
		return auth;
	}
	
	public String getUpToken() {
		// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
		return getAuth().uploadToken(qiniuConfig.getBucketName());
	}

	public String getOverlayUploadToken(String key) {
		// 覆盖上传
		// <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
		// 如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。
		// 第三个参数是token的过期时间
		return getAuth().uploadToken(qiniuConfig.getBucketName(), key);
	}

}

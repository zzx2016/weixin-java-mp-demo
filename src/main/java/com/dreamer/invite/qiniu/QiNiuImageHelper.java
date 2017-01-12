package com.dreamer.invite.qiniu;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dreamer.invite.config.QiNiuConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

/**
 * 
 * @author sima
 *
 */
@Component
public class QiNiuImageHelper {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	QiNiuConfig qiniuConfig;
	
	@Autowired
	private QiNiuSingleton singleton ;// 授权，所有操作的前提

	/**
	 * 文件上传，包括图片和文本，普通上传不覆盖
	 * @param localPath  本地全路径包括文件名
	 * @param name 保存到服务器上的文件名称
	 * @return 上传成功返回文件路径，失败返回空字符串
	 */
	public String Upload(String localPath, String name) {
		try {
			logger.debug("普通上传，本地路径为：" + toString() + "\t上传到服务器上文件名:" + name);
			Response res = singleton.getUploadManager().put(localPath, name, singleton.getUpToken());
			if(res.isOK()){
				JSONObject obj = JSONObject.parseObject(res.bodyString());
				obj.put("url",qiniuConfig.getDomain() + name);
				return obj.toJSONString();
			}
			logger.info("返回json解析出错,返回结果是:"+res.toString());
			return "";
		} catch (QiniuException e) {
			logger.error("图片普通上传失败", e.response.toString());
		}
		return "";
	}

	/**
	 * 覆盖上传，同名文件会覆盖掉之前的文件
	 * @param localPath 本地文件的路径你个
	 * @param name  服务器上文件名称
	 * @return  上傳圖片的路徑
	 */
	public String OverlayUpload(String localPath, String name) {
		try {
			logger.debug("覆盖上传，本地路径为：" + toString() + "\t上传到服务器上文件名:" + name);
			Response res = singleton.getUploadManager().put(localPath, name, singleton.getOverlayUploadToken(name));
			if(res.isOK()){
				return res.bodyString();
			}
			logger.info("返回json解析出错,返回结果是:"+res.toString());
			return "";
		} catch (QiniuException e) {
			logger.error("图片覆盖上传失败", e.response.toString());
		}
		return "";
	}

	/**
	 * 根据文件名下载服务器上的文件
	 * @param key 文件名
	 * @return 文件路径
	 */
	public String download(String key) {
		// 调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
		try {
			logger.debug("获取文件路径，文件名为：" + key);
			String downloadRUL = singleton.getAuth().privateDownloadUrl(qiniuConfig.getDomain() + key, 3600);
			return downloadRUL;
		} catch (Exception e) {
			logger.error("图片获取失败", e);
		}
		return "";
	}

	/**
	 * 根據文件名刪除服務器上的文件
	 * @param key  文件名
	 */
	public boolean delete(String key) {
		try {
			// 调用delete方法移动文件
			logger.debug("刪除文件，文件名是："+key);
			System.out.println(qiniuConfig.getBucketName());
			System.out.println(key);
			System.out.println(singleton.getBucketManager());
			singleton.getBucketManager().delete(qiniuConfig.getBucketName(), key);
			return true;
		} catch (QiniuException e) {
			e.printStackTrace();
			logger.error("刪除文件失敗",e.response.toString());
		}
		return false;
	}

}

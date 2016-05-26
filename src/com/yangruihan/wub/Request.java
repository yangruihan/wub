package com.yangruihan.wub;

import java.util.List;
import java.util.Map;

/**
 * 请求接口
 * @author Yrh
 *
 */
public interface Request {

	/**
	 * 缓冲大小
	 */
	static final int REQ_BUFFER_SIZE = 2048;
	
	/**
	 * 得到头部数据
	 * @return
	 */
	List<String> getHeader();
	
	/**
	 * 得到身体
	 * @return
	 */
	String getBody();

	/**
	 * 得到 Uri
	 * @return
	 */
	String getUri();
	
	/**
	 * 得到所有参数
	 * @return
	 */
	Map<String, String> getParameters();
	
	/**
	 * 得到某一个参数
	 * @return
	 */
	String getParameter(String key);
	
	/**
	 * 得到 Post 方法中的某一个参数
	 * @param key
	 * @return
	 */
	String post(String key);
	
	/**
	 * 得到 Get 方法中的某一个参数
	 * @param key
	 * @return
	 */
	String get(String key);
}

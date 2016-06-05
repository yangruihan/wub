package com.yangruihan.wub.request;

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
	byte[] getBody();

	/**
	 * 得到 Uri
	 * @return
	 */
	String getUri();
	
	/**
	 * 得到请求方法
	 * @return
	 */
	String getMethod();
	
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
	 * 设置一个参数
	 * @param key
	 * @param value
	 */
	void setParameter(String key, String value);
	
	/**
	 * 得到所有Cookie
	 * @return
	 */
	Map<String, Cookie> getCookies();
	
	/**
	 * 得到一个指定的Cookie
	 * @param cookieName
	 * @return
	 */
	Cookie getCookie(String cookieName);
	
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

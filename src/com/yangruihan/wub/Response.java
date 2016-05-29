package com.yangruihan.wub;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 响应接口
 * @author Yrh
 *
 */
public interface Response {

	/**
	 * 缓冲大小
	 */
	static final int RES_BUFFER_SIZE = 2048;
	
	/**
	 * 发送响应
	 * @throws IOException
	 */
	void send() throws IOException;
	
	/**
	 * 设置响应
	 * @throws IOException
	 */
	Response setResponse() throws IOException;
	
	/**
	 * 设置响应
	 * @param header
	 * @param body
	 * @throws IOException
	 */
	Response setResponse(String header, byte[] body) throws IOException;

	/**
	 * 设置头
	 */
	Response setHeader();
	
	/**
	 * 设置头
	 * @param header
	 */
	Response setHeader(String header);
	
	/**
	 * 设置返回状态
	 * @param str
	 */
	Response setStatus(int status, String describe);
	
	/**
	 * 添加一个头部（如果已存在，则先删除）
	 * @param key
	 * @param value
	 */
	Response addHeader(String key, String value);
	
	/**
	 * 添加一个头部
	 * @param key 
	 * @param value
	 * @param flag 如果已存在是否删除
	 */
	Response addHeader(String key, String value, boolean flag);
	
	/**
	 * 删除一个头部
	 * @param key
	 */
	Response deleteHeader(String key);
	
	/**
	 * 设置身体
	 */
	Response setBody();
	
	/**
	 * 设置身体
	 * @param body
	 */
	Response setBody(byte[] body);
	
	/**
	 * 设置输出流
	 * @param output
	 */
	void setOutputStream(OutputStream output);
	
	/**
	 * 添加 Cookie
	 * @param cookie
	 */
	Response addCookie(Cookie cookie);
	
	/**
	 * 得到请求
	 * @return
	 */
	Request getRequest();

	/**
	 * 得到头部
	 * @return
	 */
	List<String> getHeader();

	/**
	 * 得到身体
	 * @return
	 */
	byte[] getBody();
}

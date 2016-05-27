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
	void setResponse() throws IOException;
	
	/**
	 * 设置响应
	 * @param header
	 * @param body
	 * @throws IOException
	 */
	void setResponse(String header, byte[] body) throws IOException;

	/**
	 * 设置头
	 */
	void setHeader();
	
	/**
	 * 设置头
	 * @param header
	 */
	void setHeader(String header);
	
	/**
	 * 添加返回状态
	 * @param str
	 */
	void addStatus(int status, String describe);
	
	/**
	 * 添加一个头部
	 * @param key
	 * @param value
	 */
	void addHeader(String key, String value);
	
	/**
	 * 删除一个头部
	 * @param key
	 */
	void deleteHeader(String key);
	
	/**
	 * 设置身体
	 */
	void setBody();
	
	/**
	 * 设置身体
	 * @param body
	 */
	void setBody(byte[] body);
	
	/**
	 * 设置输出流
	 * @param output
	 */
	void setOutputStream(OutputStream output);
	
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

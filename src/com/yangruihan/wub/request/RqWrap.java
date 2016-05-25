package com.yangruihan.wub.request;

import com.yangruihan.wub.Request;

/**
 * 请求包装类
 * 
 * @author Yrh
 *
 */
public class RqWrap {

	/**
	 * 原始请求
	 */
	private Request request;

	/**
	 * Ctor.
	 * 
	 * @param request
	 */
	public RqWrap(Request request) {
		this.request = request;
	}

	/*-----getter-----*/
	public Request getRequest() {
		return request;
	}
}
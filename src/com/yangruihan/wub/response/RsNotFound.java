package com.yangruihan.wub.response;

import com.yangruihan.wub.constant.C;
import com.yangruihan.wub.request.Request;

/**
 * 返回没有找到的响应
 * @author YRH
 *
 */
public class RsNotFound extends ResponseWrap {

	/**
	 * 错误信息
	 */
	private String errorInfo;
	
	/**
	 * Ctor.
	 * @param request
	 */
	public RsNotFound(Request request) {
		this(request, "Not Found Page");
	}
	
	/**
	 * Ctor.
	 * @param request
	 * @param errorInfo
	 */
	public RsNotFound(Request request, String errorInfo) {
		super(request);
		this.errorInfo = errorInfo;
		
		this.setStatus(C.Response_status.NOT_FOUND, "Not Found");
		this.addHeader("Content-type", "text/html; charset=utf-8");
		if (errorInfo != null && !errorInfo.isEmpty()) {
			this.setBody(errorInfo.getBytes());
		}
	}

}

package com.yangruihan.wub.response;

import com.yangruihan.wub.Request;
import com.yangruihan.wub.ResponseWrap;
import com.yangruihan.wub.constant.Constant;

/**
 * 返回文本的响应
 * 
 * @author Yrh
 *
 */
public class RsText extends ResponseWrap {
	
	/**
	 * 文本内容
	 */
	private String text;

	/**
	 * Ctor.
	 * 
	 * @param request
	 * @param text
	 */
	public RsText(Request request, String text) {
		super(request);
		this.text = text;
		
		// 设置 body
		if (text != null && !text.isEmpty()) {
			this.setStatus(Constant.Response_status.OK, "OK");
			this.addHeader("Content-type", "text/html; charset=utf-8");
			this.setBody(text.getBytes());
		} else {
			this.setStatus(Constant.Response_status.NOT_FOUND, "Not Found");
			this.addHeader("Content-type", "text/html; charset=utf-8");
			this.setBody("<div>Resource Not Found</div>".getBytes());
		}
	}

	public String getText() {
		return text;
	}
}

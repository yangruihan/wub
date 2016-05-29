package com.yangruihan.wub.response;

import com.yangruihan.wub.Request;
import com.yangruihan.wub.ResponseWrap;

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
			this.setStatus(200, "OK");
			this.addHeader("Content-type", "text/html; charset=utf-8");
			this.setBody(text.getBytes());
		} else {
			this.setStatus(404, "Not Found");
			this.addHeader("Content-type", "text/html; charset=utf-8");
			this.setBody("<div>Resource Not Found</div>".getBytes());
		}
	}

	public String getText() {
		return text;
	}
}

package com.yangruihan.wub.response;

import java.io.IOException;

import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.ResponseWrap;

/**
 * 返回文本的响应
 * @author Yrh
 *
 */
public class RsText extends ResponseWrap {
	
	/**
	 * 文本
	 */
	private String text;

	/**
	 * Ctor.
	 * @param request
	 * @param text
	 */
	public RsText(Request request, String text) {
		super(request);
		this.text = text;
	}
	
	@Override
	public void send() throws IOException {
		this.addStatus(200, "OK");
		this.addHeader("Content-type", "text/html");
		this.setBody(this.text);
		super.send();
	}
}

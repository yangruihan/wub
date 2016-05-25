package com.yangruihan.wub.response;

import java.io.IOException;

import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;

/**
 * 返回文本的响应
 * @author Yrh
 *
 */
public class RsText extends Response {
	
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
		setResponse(null, this.text);
		super.send();
	}
}

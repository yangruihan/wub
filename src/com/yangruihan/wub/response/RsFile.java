package com.yangruihan.wub.response;

import java.io.IOException;

import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.ResponseWrap;
import com.yangruihan.wub.util.FileHelper;

/**
 * 返回文件的响应
 * 
 * @author Yrh
 *
 */
public class RsFile extends ResponseWrap {

	/**
	 * 页面资源
	 */
	private String uri;
	
	/**
	 * 文件类型
	 */
	private String contentType;

	/**
	 * Ctor.
	 * 
	 * @param response
	 * @param uri
	 */
	public RsFile(Request request, String uri) {
		super(request);
		this.uri = uri;
	}
	
	/**
	 * Ctor.
	 * @param request
	 * @param uri
	 * @param contentType
	 */
	public RsFile(Request request, String uri, String contentType) {
		super(request);
		this.uri = uri;
		this.contentType = contentType;
	}

	@Override
	public void send() throws IOException {
		setResponse();
		super.send();
	}

	@Override
	public void setResponse() throws IOException {
		String content = FileHelper.getContent(this.uri);
		if (content != null) {
			setBody(content.getBytes());
			this.addStatus(200, "OK");
			if (this.contentType != null && !this.contentType.isEmpty()) {
				this.addHeader("Content-Type", this.contentType);
			}
		} else {
			// 找不到文件
			this.addStatus(404, "File NOT Fount");
			this.addHeader("Content-Type", "text/html; charset=utf-8");
			this.addHeader("Content-Length", "23");
			this.setBody("<h1>File Not Found</h1>".getBytes());
		}

	}
}

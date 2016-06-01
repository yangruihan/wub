package com.yangruihan.wub.response;

import java.io.IOException;

import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.ResponseWrap;
import com.yangruihan.wub.constant.Constant;
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
	 * @param request
	 * @param uri
	 * @throws IOException 
	 */
	public RsFile(Request request, String uri) throws IOException {
		this(request, uri, null);
	}
	
	/**
	 * Ctor.
	 * @param request
	 * @param uri
	 * @param contentType
	 * @throws IOException 
	 */
	public RsFile(Request request, String uri, String contentType) throws IOException {
		super(request);
		this.uri = uri;
		if (contentType != null && !contentType.isEmpty()) {
			this.contentType = contentType;
		}
		
		String content = FileHelper.getContent(this.uri);
		if (content != null) {
			this.setStatus(Constant.Response_status.OK, "OK");
			setBody(content.getBytes());
			if (this.contentType != null && !this.contentType.isEmpty()) {
				this.addHeader("Content-Type", this.contentType);
			}
		} else {
			// 找不到文件
			this.setStatus(Constant.Response_status.NOT_FOUND, "File NOT Fount");
			this.addHeader("Content-Type", "text/html; charset=utf-8");
			this.addHeader("Content-Length", "23");
			this.setBody("<h1>File Not Found</h1>".getBytes());
		}
	}

	public String getUri() {
		return uri;
	}

	public String getContentType() {
		return contentType;
	}
}

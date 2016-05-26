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
	 * Ctor.
	 * 
	 * @param response
	 * @param uri
	 */
	public RsFile(Request request, String uri) {
		super(request);
		this.uri = uri;
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
			setBody(content);
		} else {
			// 找不到文件
			String header = "HTTP/1.1 404 File NOT Fount\r\n" + "Content-Type: text/html\r\n"
					+ "Content-Length: 23\r\n";
			this.setHeader(header);
			this.setBody("<h1>File Not Found</h1>");
		}

	}
}

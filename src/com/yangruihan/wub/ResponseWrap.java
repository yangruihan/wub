package com.yangruihan.wub;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import com.yangruihan.wub.constant.Constant;

/**
 * 响应包装类
 * 
 * @author Yrh
 *
 */
public class ResponseWrap implements Response {

	/**
	 * 响应的请求
	 */
	private final Request request;

	/**
	 * 输出流
	 */
	private OutputStream output;

	/**
	 * 响应头
	 */
	private final List<String> header;

	/**
	 * 响应体
	 */
	private byte[] body;

	/**
	 * Ctor.
	 * 
	 * @param request
	 */
	public ResponseWrap(Request request) {
		this.request = request;
		header = new LinkedList<>();
	}

	/**
	 * 发送响应
	 * 
	 * @throws IOException
	 */
	@Override
	public void send() throws IOException {
		StringBuffer header = new StringBuffer();
		for (String s : this.header) {
			header.append(s + "\r\n");
		}

		//
		// 打印信息
		//
		System.out.println("Response: " + this.header.toString());

		String response = (header.length() == 0 ? "" : header.toString()) + "\r\n"
				+ (this.body == null ? "" : new String(this.body)) + "\r\n";

		// 提交响应到输出流
		output.write(response.getBytes());
		output.flush();
	}

	/**
	 * 设置响应
	 * 
	 * @throws IOException
	 */
	@Override
	public Response setResponse() throws IOException {
		setHeader();
		setBody();
		
		return this;
	}

	/**
	 * 设置响应
	 * 
	 * @param header
	 * @param body
	 * @throws IOException
	 */
	@Override
	public Response setResponse(String header, byte[] body) throws IOException {
		setHeader(header);
		setBody(body);
		
		return this;
	}

	/**
	 * 设置响应头
	 */
	@Override
	public Response setHeader() {
		if (this.header.size() != 0) {
			this.header.clear();
		}
		
		return this;
	}

	/**
	 * 设置响应头
	 * 
	 * @param header
	 */
	@Override
	public Response setHeader(String header) {
		if (header == null)
			return this;

		if (this.header.size() != 0) {
			this.header.clear();
		}

		String[] headers = header.split("\r\n");

		for (String h : headers) {
			if (!h.isEmpty()) {
				this.header.add(h);
			}
		}
		
		return this;
	}

	/**
	 * 设置响应体
	 */
	@Override
	public Response setBody() {
		if (this.body.length != 0) {
			this.body = new byte[0];
		}
		
		return this;
	}

	/**
	 * 设置响应体
	 * 
	 * @param body
	 */
	@Override
	public Response setBody(byte[] body) {
		this.body = body;
		
		return this;
	}

	/**
	 * 设置输出流
	 */
	@Override
	public void setOutputStream(OutputStream output) {
		this.output = output;
	}

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public List<String> getHeader() {
		return header;
	}

	@Override
	public byte[] getBody() {
		return body;
	}

	@Override
	public Response addHeader(String key, String value, boolean flag) {
		if (flag == true) {
			// 先将已存在的头删除，避免冗余
			deleteHeader(key);
		}
		this.header.add(String.format("%s: %s", key, value));
		
		return this;
	}

	@Override
	public Response addHeader(String key, String value) {
		// 先将已存在的头删除，避免冗余
		deleteHeader(key);
		this.header.add(String.format("%s: %s", key, value));
		
		return this;
	}

	@Override
	public Response deleteHeader(String key) {
		if (this.header.size() == 0)
			return this;

		int del = -1;
		for (int i = 1; i < this.header.size(); i++) {
			String s = this.header.get(i);
			if (s.split(":")[0].equals(key)) {
				del = i;
				break;
			}
		}

		if (del != -1)
			this.header.remove(del);
		
		return this;
	}

	@Override
	public Response setStatus(int status, String describe) {
		String s = String.format("%s %d %s", Constant.Http.HTTP_VERSION, status, describe);
		if (this.header.size() == 0) {
			this.header.add(s);
		} else if (this.header.get(0).contains(":")) {
			this.header.add(0, s);
		} else {
			this.header.remove(0);
			this.header.add(s);
		}
		
		return this;
	}

	@Override
	public Response addCookie(Cookie cookie) {
		StringBuilder setCookie = new StringBuilder();
		setCookie.append(cookie.getName() + "=" + cookie.getValue() + "; ");

		if (cookie.getComment() != null) {
			setCookie.append("Comment=" + cookie.getComment() + "; ");
		}

		if (cookie.getDomain() != null) {
			setCookie.append("Domain=" + cookie.getDomain() + "; ");
		}

		if (cookie.getMaxAge() != -1) {
			setCookie.append("Max-Age=" + cookie.getMaxAge() + "; ");
		}

		if (cookie.getPath() != null) {
			setCookie.append("Path=" + cookie.getPath() + "; ");
		}

		if (cookie.getSecure()) {
			setCookie.append("Secure; ");
		}

		if (cookie.getVersion() != 0) {
			setCookie.append("Version=" + cookie.getVersion() + "; ");
		}

		if (setCookie.toString().endsWith("; ")) {
			setCookie.delete(setCookie.length() - 2, setCookie.length());
		}

		addHeader("Set-Cookie", setCookie.toString(), false);
		
		return this;
	}

	// /**
	// * 判断是否已经包含这个头
	// *
	// * @param key
	// * @return
	// */
	// private boolean containHeader(String key) {
	// if (this.header.size() == 0)
	// return false;
	//
	// for (int i = 1; i < this.header.size(); i++) {
	// String s = this.header.get(i);
	// if (s.split(":")[0].equals(key)) {
	// return true;
	// }
	// }
	// return false;
	// }
}

package com.yangruihan.wub;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import com.yangruihan.wub.constant.Web;

/**
 * 响应包装类
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
	private String body;

	/**
	 * Ctor.
	 * 
	 * @param output
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

		String response = (header.length() == 0 ? "" : header.toString()) + "\r\n"
				+ (this.body == null ? "" : this.body) + "\r\n";

//		System.out.println("\n-----Response-----");
//		System.out.println(response);
//		System.out.println("--------------------\n");

		output.write(response.getBytes());
		output.flush();
	}

	/**
	 * 设置响应
	 * 
	 * @throws IOException
	 */
	@Override
	public void setResponse() throws IOException {
		setHeader();
		setBody();
	}

	/**
	 * 设置响应
	 * 
	 * @param header
	 * @param body
	 * @throws IOException
	 */
	@Override
	public void setResponse(String header, String body) throws IOException {
		setHeader(header);
		setBody(body);
	}

	/**
	 * 设置响应头
	 */
	@Override
	public void setHeader() {
		if (this.header.size() != 0) {
			this.header.clear();
		}
	}

	/**
	 * 设置响应头
	 * 
	 * @param header
	 */
	@Override
	public void setHeader(String header) {
		if (header == null) return;
		
		if (this.header.size() != 0) {
			this.header.clear();
		}

		String[] headers = header.split("\r\n");

		for (String h : headers) {
			if (!h.isEmpty()) {
				this.header.add(h);
			}
		}
	}

	/**
	 * 设置响应体
	 */
	@Override
	public void setBody() {
		if (!this.body.isEmpty()) {
			this.body = "";
		}
	}

	/**
	 * 设置响应体
	 * 
	 * @param body
	 */
	@Override
	public void setBody(String body) {
		this.body = body;
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
	public String getBody() {
		return body;
	}

	@Override
	public void addHeader(String key, String value) {
		// 先将已存在的头删除，避免冗余
		deleteHeader(key);
		this.header.add(String.format("%s: %s", key, value));
	}

	@Override
	public void deleteHeader(String key) {
		if (this.header.size() == 0) return;
		
		int del = -1;
		for (int i = 1; i < this.header.size(); i++) {
			String s = this.header.get(i);
			if (s.split(":")[0].equals(key)) {
				del = i;
				break;
			}
		}
		
		if (del != -1) this.header.remove(del);
	}

	@Override
	public void addStatus(int status, String describe) {
		String s = String.format("%s %d %s", Web.HTTP_VERSION, status, describe);
		if (this.header.size() == 0) {
			this.header.add(s);
		} else if (this.header.get(0).contains(":")) {
			this.header.add(0, s);
		} else {
			this.header.remove(0);
			this.header.add(s);
		}
	}
}

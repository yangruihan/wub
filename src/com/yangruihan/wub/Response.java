package com.yangruihan.wub;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Response 响应类
 * 
 * @author Yrh
 *
 */
public class Response {

	/**
	 * 缓冲大小
	 */
	public static final int RES_BUFFER_SIZE = 2048;

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
	public Response(Request request) {
		this.request = request;
		header = new LinkedList<>();
	}

	/**
	 * 发送响应
	 * 
	 * @throws IOException
	 */
	public void send() throws IOException {
		StringBuffer header = new StringBuffer();
		for (String s : this.header) {
			header.append(s + "\r\n");
		}

		String response = (header.length() == 0 ? "" : header.toString()) + "\r\n"
				+ (this.body == null ? "" : this.body) + "\r\n";

		System.out.println("\n-----Response-----");
		System.out.println(response);
		System.out.println("--------------------\n");

		output.write(response.getBytes());
		output.flush();
	}

	/**
	 * 设置响应
	 * 
	 * @throws IOException
	 */
	protected void setResponse() throws IOException {
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
	protected void setResponse(String header, String body) throws IOException {
		setHeader(header);
		setBody(body);
	}

	/**
	 * 设置响应头
	 */
	protected void setHeader() throws IOException {
		if (this.header.size() != 0) {
			this.header.clear();
		}
	}

	/**
	 * 设置响应头
	 * 
	 * @param header
	 */
	protected void setHeader(String header) throws IOException {
		if (header == null) return;
		
		if (this.header.size() != 0) {
			this.header.clear();
		}

		String[] headers = header.split("\r\n");

		for (String h : headers) {
			if (!h.isEmpty()) {
				this.header.add(h + "\r\n");
			}
		}
	}

	/**
	 * 设置响应体
	 */
	protected void setBody() throws IOException {
		if (!this.body.isEmpty()) {
			this.body = "";
		}
	}

	/**
	 * 设置响应体
	 * 
	 * @param body
	 */
	protected void setBody(String body) throws IOException {
		this.body = body;
	}

	/**
	 * 设置输出流
	 * 
	 * @param output
	 */
	public void setOutput(OutputStream output) {
		this.output = output;
	}

	/*----- getter -----*/
	public Request getRequest() {
		return request;
	}

	public OutputStream getOutput() {
		return output;
	}

	public List<String> getHeader() {
		return header;
	}

	public String getBody() {
		return body;
	}
}

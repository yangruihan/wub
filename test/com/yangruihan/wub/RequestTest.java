package com.yangruihan.wub;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class RequestTest {

	/**
	 * 缓冲大小
	 */
	public static final int REQ_BUFFER_SIZE = 2048;

	/**
	 * 输入流
	 */
	private final InputStream input;

	/**
	 * 头部
	 */
	private final List<String> header;

	/**
	 * 身体
	 */
	private String body;

	/**
	 * 访问资源
	 */
	private String uri;

	/**
	 * Ctor.
	 * @param input
	 * @throws IOException
	 */
	public RequestTest(InputStream input) throws IOException {
		this.input = input;
		this.header = new LinkedList<>();
		this.body = "";
		this.uri = "";
		
		parse();
	}

	/**
	 * 解析输入流
	 */
	private void parse() throws IOException {
		StringBuffer request = new StringBuffer(REQ_BUFFER_SIZE);

		int i;
		byte[] buffer = new byte[REQ_BUFFER_SIZE];

		i = input.read(buffer);

		for (int j = 0; j < i; j++) {
			request.append((char) buffer[j]);
		}

		if (request.toString().length() > 0) {
			// 解析头
			parseHeader(request.toString());
	
			// 解析身体
			parseBody(request.toString());
	
			// 解析 Uri
			parseUri();
		}

		System.out.println("\n-----Request-----");
		System.out.println("-Request Header:");
		for (String s : this.header) {
			System.out.println(s);
		}
		System.out.println("\n-Request Body:");
		System.out.println(this.body);
		System.out.println("\n-Request Uri:");
		System.out.println(this.uri);
		System.out.println("--------------------");
	}

	/**
	 * 解析头
	 * @param request
	 */
	private void parseHeader(String request) {
		int i = request.indexOf("\r\n\r\n") + 2;

		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < i; j++) {
			if (request.charAt(j) == '\r' && request.charAt(j + 1) == '\n') {
				this.header.add(sb.toString());
				sb.setLength(0); // 清空 StringBuffer

				if (++j >= i) break;
				else continue;
			}
			sb.append(request.charAt(j));
		}
	}
	
	/**
	 * 解析身体
	 * @param request
	 */
	private void parseBody(String request) {
		int i = request.indexOf("\r\n\r\n");
		this.body = request.substring(i + 4);
	}
	
	/**
	 * 解析 Uri
	 * 
	 * @return
	 */
	private void parseUri() {
		this.uri = this.header.get(0).split(" ")[1];
	}
	
	/*-----setter & getter-----*/
	public InputStream getInput() {
		return input;
	}

	public List<String> getHeader() {
		return header;
	}

	public String getBody() {
		return body;
	}

	public String getUri() {
		return uri;
	}
}

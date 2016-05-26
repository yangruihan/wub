package com.yangruihan.wub;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Request 请求包装类
 * 
 * @author Yrh
 *
 */
public class RequestWrap implements Request {

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
	 * Ctor.
	 * 
	 * @param input
	 * @throws IOException
	 */
	public RequestWrap(InputStream input) throws IOException {
		this.input = input;
		this.header = new LinkedList<>();
		this.body = "";

		// 解析输入流
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

		if (request.toString().trim().length() > 0) {
			// 解析头
			parseHeader(request.toString());

			// 解析身体
			parseBody(request.toString());
		}

		// System.out.println("\n-----Request-----");
		// System.out.println("-Request Header:");
		// for (String s : this.header) {
		// System.out.println(s);
		// }
		// System.out.println("\n-Request Body:");
		// System.out.println(this.body);
		// System.out.println("\n-Request Uri:");
		// System.out.println(getUri());
		// System.out.println("--------------------");

		if (getHeader() != null && getHeader().size() > 0) {
			System.out.println(getHeader().get(0));
		}
	}

	/**
	 * 解析头
	 * 
	 * @param request
	 */
	private void parseHeader(String request) {
		int i = request.indexOf("\r\n\r\n") + 2;

		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < i; j++) {
			if (request.charAt(j) == '\r' && request.charAt(j + 1) == '\n') {
				this.header.add(sb.toString());
				sb.setLength(0); // 清空 StringBuffer

				if (++j >= i)
					break;
				else
					continue;
			}
			sb.append(request.charAt(j));
		}
	}

	/**
	 * 解析身体
	 * 
	 * @param request
	 */
	private void parseBody(String request) {
		int i = request.indexOf("\r\n\r\n");
		this.body = request.substring(i + 4);
	}

	/*----- getter -----*/
	/**
	 * 得到头
	 * 
	 * @return
	 */
	public List<String> getHeader() {
		return Collections.unmodifiableList(this.header);
	}

	/**
	 * 得到身体
	 * 
	 * @return
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 得到 Uri
	 * 
	 * @return
	 */
	public String getUri() {
		if (header == null || header.size() == 0) {
			return "";
		}
		return this.header.get(0).split(" ")[1];
	}

	/**
	 * 得到所有参数
	 * 
	 * @return
	 */
	public Map<String, String> getParameters() {
		Map<String, String> ret = new HashMap<>();

		String[] headerValue = this.getUri().trim().split("\\?");
		if (headerValue != null && headerValue.length == 2) {
			String[] headerValues = headerValue[1].split("&");

			// 得到头部参数
			if (headerValues != null && headerValues.length > 0) {
				for (String value : headerValues) {
					String[] kv = value.split("=");
					if (kv.length == 2) {
						ret.put(kv[0], kv[1]);
					}
				}
			}
		}

		String[] bodyValues = this.body.trim().split("&");
		// 得到身体参数
		if (bodyValues != null && bodyValues.length > 0) {
			for (String value : bodyValues) {
				String[] kv = value.split("=");
				if (kv.length == 2) {
					ret.put(kv[0], kv[1]);
				}
			}
		}

		return ret;
	}

	/**
	 * 得到某一个参数
	 * 
	 * @param key
	 * @return
	 */
	public String getParameter(String key) {
		if (!key.isEmpty()) {
			return getParameters().get(key);
		}
		return null;
	}

	/**
	 * 得到Post方法某个参数
	 */
	public String post(String key) {
		String[] bodyValues = this.body.trim().split("&");
		if (bodyValues != null && bodyValues.length > 0) {
			for (String value : bodyValues) {
				String[] kv = value.split("=");
				if (kv.length == 2 && kv[0].equals(key)) {
					return kv[1];
				}
			}
		}
		return null;
	}

	/**
	 * 得到get方法某个参数
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String[] headerValue = this.getUri().trim().split("\\?");
		if (headerValue != null && headerValue.length == 2) {
			String[] headerValues = headerValue[1].split("&");
			if (headerValues != null && headerValues.length > 0) {
				for (String value : headerValues) {
					String[] kv = value.split("=");
					if (kv.length == 2 && kv[0].equals(key)) {
						return kv[1];
					}
				}
			}
		}
		return null;
	}
}

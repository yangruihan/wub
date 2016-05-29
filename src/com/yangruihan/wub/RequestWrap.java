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
	private byte[] body;

	/**
	 * Cookies
	 */
	private Map<String, Cookie> cookies;
	
	/**
	 * Uri
	 */
	private String uri;
	
	/**
	 * 所有参数
	 */
	private Map<String, String> parameters;
	
	/**
	 * POST 方法传递的参数
	 */
	private Map<String, String> postParameters;
	
	/**
	 * GET 方法传递的参数
	 */
	private Map<String, String> getParameters;
	
	/**
	 * Ctor.
	 * 
	 * @param input
	 * @throws IOException
	 */
	public RequestWrap(InputStream input) throws IOException {
		this.input = input;
		this.header = new LinkedList<>();
		this.body = new byte[0];
		this.cookies = new HashMap<>();
		this.uri = "";
		this.parameters = new HashMap<>();
		this.postParameters = new HashMap<>();
		this.getParameters = new HashMap<>();

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

			// 解析 Uri
			parseUri();
			
			// 解析 Cookie
			parseCookies();
			
			// 解析参数
			parseParameters();
		}

		//
		// 打印信息
		//
		if (getHeader() != null && getHeader().size() > 0) {
			System.out.println("Request: " + getHeader().get(0));
		}
	}

	/**
	 * 解析参数
	 */
	private void parseParameters() {
		String[] headerValue = this.getUri().trim().split("\\?");
		if (headerValue != null && headerValue.length == 2) {
			String[] headerValues = headerValue[1].split("&");

			// 得到头部参数
			if (headerValues != null && headerValues.length > 0) {
				for (String value : headerValues) {
					String[] kv = value.split("=");
					if (kv.length == 2) {
						this.getParameters.put(kv[0], kv[1]);
						this.parameters.put(kv[0], kv[1]);
					}
				}
			}
		}
		

		String[] bodyValues = new String(this.body).trim().split("&");
		// 得到身体参数
		if (bodyValues != null && bodyValues.length > 0) {
			for (String value : bodyValues) {
				String[] kv = value.split("=");
				if (kv.length == 2) {
					this.postParameters.put(kv[0], kv[1]);
					this.parameters.put(kv[0], kv[1]);
				}
			}
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
		this.body = request.substring(i + 4).getBytes();
	}
	
	/**
	 * 解析 Uri
	 */
	private void parseUri() {
		if (header == null || header.size() == 0) {
			return;
		}
		
		this.uri = this.header.get(0).split(" ")[1];
	}

	/**
	 * 解析Cookies
	 */
	private void parseCookies() {
		if (header == null || header.size() == 0) {
			return;
		}
		
		Map<String, Cookie> cookies = new HashMap<>();

		for (String header : this.header) {
			String[] vk = header.split(":");
			if (vk.length == 2 && vk[0].equals("Cookie")) {
				for (String cookieStr : vk[1].trim().split("; ")) {
					if (cookieStr.split("=").length == 2) {
						Cookie cookie = new Cookie(cookieStr.split("=")[0], cookieStr.split("=")[1]);
						cookies.put(cookie.getName(), cookie);
					}
				}
			}
		}
		
		this.cookies = cookies;
	}

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
	public byte[] getBody() {
		return body;
	}

	/**
	 * 得到 Uri
	 * 
	 * @return
	 */
	public String getUri() {
		return this.uri;
	}

	/**
	 * 得到所有参数
	 * 
	 * @return
	 */
	public Map<String, String> getParameters() {
		return this.parameters;
	}

	/**
	 * 得到某一个参数
	 * 
	 * @param key
	 * @return
	 */
	public String getParameter(String key) {
		return this.parameters.get(key);
	}

	/**
	 * 得到Post方法某个参数
	 */
	public String post(String key) {
		return this.postParameters.get(key);
	}

	/**
	 * 得到get方法某个参数
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return getParameters.get(key);
	}

	@Override
	public Map<String, Cookie> getCookies() {
		return this.cookies;
	}

	@Override
	public Cookie getCookie(String cookieName) {
		return this.cookies.get(cookieName);
	}

	@Override
	public void setParameter(String key, String value) {
		if (key != null && !key.isEmpty() && value != null && !value.isEmpty()) {
			this.parameters.put(key, value);
		}
	}
}

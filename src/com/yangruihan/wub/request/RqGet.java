package com.yangruihan.wub.request;

import com.yangruihan.wub.Request;

public class RqGet extends RqWrap {

	/**
	 * Ctor.
	 * @param request
	 */
	public RqGet(Request request) {
		super(request);
	}

	/**
	 * 得到一个参数
	 * @param parameter
	 * @return
	 */
	public String getParameter(String parameter) {
		String[] values = getRequest().getUri().split("\\?")[1].split("&");
		
		for (String value : values) {
			String[] kv = value.split("=");
			if (kv[0].equals(parameter)) {
				return kv[1];
			}
		}
		
		return null;
	}
}

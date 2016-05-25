package com.yangruihan.wub.request;

import java.io.IOException;
import java.io.InputStream;

import com.yangruihan.wub.Request;

/**
 * 装饰带有post数据
 * @author Yrh
 *
 */
public class RqPost extends RqWrap {
	
	/**
	 * Ctor.
	 * @param request
	 */
	public RqPost(Request request) {
		super(request);
	}
	
	/**
	 * 得到一个参数
	 * @param parameter
	 * @return
	 */
	public String getParameter(String parameter) {
		String[] values = getRequest().getBody().trim().split("&");
		
		for (String value : values) {
			String[] kv = value.split("=");
			if (kv[0].equals(parameter)) {
				return kv[1];
			}
		}
		
		return null;
	}
}

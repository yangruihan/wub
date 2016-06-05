package com.yangruihan.wub.middleware;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.yangruihan.wub.response.Response;

/**
 * 通用中间件类，为响应头添加通用部分
 * @author Yrh
 *
 */
public class CommonMiddleware implements Middleware {

	@Override
	public Response processResponse(Response origin) {
		addDate(origin); // 添加日期头
		addContentLength(origin); // 添加内容长度头
		
		return origin;
	}

	/**
	 * 添加日期头
	 * @param origin
	 */
	private void addDate(Response origin) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		origin.addHeader("Date", sdf.format(new Date()));
	}
	
	/**
	 * 添加内容长度头
	 * @param origin
	 */
	private void addContentLength(Response origin) {
		origin.addHeader("Content-Length", origin.getBody().length + "");
	}
}

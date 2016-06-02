package com.yangruihan.wub.middleware;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.yangruihan.wub.response.Response;

/**
 * 添加 Date
 * @author YRH
 *
 */
public class DateMiddleware implements Middleware {

	@Override
	public Response processResponse(Response origin) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return origin.addHeader("Date", sdf.format(new Date()));
	}

}

package com.yangruihan.wub.middleware;

import com.yangruihan.wub.response.Response;

/**
 * 添加内容长度
 * @author Yrh
 *
 */
public class ContentLenMiddleware implements Middleware {

	@Override
	public Response processResponse(Response origin) {
		return origin.addHeader("Content-Length", origin.getBody().length + "");
	}

}

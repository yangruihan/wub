package com.yangruihan.wub.middleware;

import com.yangruihan.wub.response.Response;

/**
 * 中间件接口
 * @author YRH
 *
 */
public interface Middleware {

	/**
	 * 对 response 进行处理
	 * @param origin 原始 response
	 * @return
	 */
	Response processResponse(Response origin);
}

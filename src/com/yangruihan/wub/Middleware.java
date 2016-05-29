package com.yangruihan.wub;

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

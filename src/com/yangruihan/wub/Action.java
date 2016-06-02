package com.yangruihan.wub;

import java.io.IOException;

import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.response.Response;

/**
 * 动作接口
 * @author Yrh
 *
 */
public interface Action {

	/**
	 * 执行一个动作
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	Response action(Request request) throws IOException;
}

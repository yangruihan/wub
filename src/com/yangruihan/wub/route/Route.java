package com.yangruihan.wub.route;

import java.io.IOException;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.response.Response;

/**
 * 路由接口
 * 
 * @author Yrh
 *
 */
public interface Route {

	/**
	 * 路由方法，接受一个request返回一个response
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	Response route(Request request) throws IOException;

	/**
	 * 添加一个Url映射，并指定该Action处理什么方法的请求
	 * 
	 * @param url
	 * @param action
	 * @param method
	 * @return
	 */
	Route addUrl(String url, Action action, String method);

	/**
	 * 添加一个Url映射，默认为 GET 方法
	 * 
	 * @param url
	 * @param action
	 * @return
	 */
	Route addUrl(String url, Action action);
}

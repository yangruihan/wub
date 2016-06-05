package com.yangruihan.wub.route;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.constant.C;
import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.response.Response;
import com.yangruihan.wub.response.RsNotFound;

/**
 * 基础路由类
 * @author Yrh
 *
 */
public class RouteWrap implements Route {

	/**
	 * URL 映射
	 */
	protected Map<String, Action> maps;
	
	/**
	 * Ctor.
	 * @param maps
	 */
	public RouteWrap() {
		maps = new HashMap<>();
	}
	
	@Override
	public Route addUrl(String url, Action action) {
		return addUrl(url, action, C.Http.GET);
	}
	
	@Override
	public Route addUrl(String url, Action action, String method) {
		if (url == null) {
			throw new IllegalArgumentException("Url cannot be null");
		}
		
		if (method == null || !(method.equals(C.Http.GET) || method.equals(C.Http.POST))) {
			throw new IllegalArgumentException("Method Name Error");
		}
		
		this.maps.put(url + ":" + method, action);
		
		return this;
	}

	@Override
	public Response route(Request request) throws IOException {
		String uri = request.getUri().split("\\?")[0].trim();
		
		Iterator<Map.Entry<String, Action>> iter = maps.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Action> entry = iter.next();
			String key = entry.getKey();
			String url = key.split(":")[0];
			String method = key.split(":")[1];

			if (uri.equals(url) && method.equals(request.getMethod())) {
				return entry.getValue().action(request);
			}
		}
		
		// 默认返回没有找到页面的响应
		return new RsNotFound(request);
	}
}

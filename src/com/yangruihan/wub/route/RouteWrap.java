package com.yangruihan.wub.route;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.yangruihan.wub.Action;
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
	public RouteWrap(Map<String, Action> maps) {
		this.maps = maps;
	}

	@Override
	public Response route(Request request) throws IOException {
		String uri = request.getUri().split("\\?")[0].trim();
		
		Iterator<Map.Entry<String, Action>> iter = maps.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Action> entry = iter.next();
			String url = entry.getKey();

			if (uri.equals(url)) {
				return entry.getValue().action(request);
			}
		}
		
		// 默认返回没有找到页面的响应
		return new RsNotFound(request);
	}
}

package com.yangruihan.wub;

import java.io.IOException;
import java.util.Map;

/**
 * 路由接口
 * @author Yrh
 *
 */
public abstract class Route {

	protected Map<String, Action> maps;
	
	public Route(Map<String, Action> maps) {
		this.maps = maps;
	}
	
	public abstract Response route(Request request) throws IOException;
}

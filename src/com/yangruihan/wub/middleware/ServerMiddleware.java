package com.yangruihan.wub.middleware;

import com.yangruihan.wub.response.Response;

/**
 * 添加 Server
 * @author YRH
 *
 */
public class ServerMiddleware implements Middleware {

	/**
	 * 服务器名
	 */
	private String server;
	
	/**
	 * Ctor.
	 * @param server
	 */
	public ServerMiddleware(String server) {
		this.server = server;
	}
	
	/**
	 * Ctor.
	 */
	public ServerMiddleware() {
		this("wub");
	}
	
	@Override
	public Response processResponse(Response origin) {
		if (this.server != null && !this.server.isEmpty()) {
			origin.addHeader("Server", this.server);
		}
		return origin;
	}

}

package com.yangruihan.wub.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import com.yangruihan.wub.Middleware;
import com.yangruihan.wub.Request;
import com.yangruihan.wub.RequestWrap;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.Route;

/**
 * 容器类
 * @author Yrh
 *
 */
public class Container {

	/**
	 * 路由
	 */
	private Route route;
	
	/**
	 * Ctor.
	 * @param route
	 */
	public Container(Route route) {
		this.route = route;
	}
	
	/**
	 * 接收一个socket，进行路由，中间件处理后，将响应发送到输出流
	 * @param socket
	 * @param middlewares
	 * @throws IOException
	 */
	public void accept(Socket socket, List<Middleware> middlewares) throws IOException {
		// 得到 request
		Request request = new RequestWrap(socket.getInputStream());
		
		// 得到输出流
		OutputStream output = socket.getOutputStream();
		
		// 路由
		Response response = this.route.route(request);
		
		// 中间件进行处理
		for (Middleware middleware : middlewares) {
			middleware.processResponse(response);
		}
		
		// 设置输出流
		response.setOutputStream(output);
		
		// 发送响应
		response.send();
		
		// 关闭 socket
		socket.close();
	}
}

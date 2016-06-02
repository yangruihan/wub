package com.yangruihan.wub.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import com.yangruihan.wub.middleware.Middleware;
import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.request.RequestWrap;
import com.yangruihan.wub.response.Response;
import com.yangruihan.wub.response.RsNotFound;
import com.yangruihan.wub.route.Route;

/**
 * 请求处理线程
 * @author Yrh
 *
 */
public class RequestHandlerThread extends Thread {

	/**
	 * 接收到的Socket
	 */
	private Socket socket;
	
	/**
	 * 中间件
	 */
	private List<Middleware> middlewares;
	
	/**
	 * 路由
	 */
	private Route route;
	
	/**
	 * 容器
	 */
	private Container container;
	
	/**
	 * Ctor.
	 * @param socket
	 * @param middlewares
	 * @param route
	 * @param container
	 */
	public RequestHandlerThread(Socket socket, List<Middleware> middlewares, Route route, Container container) {
		this.socket = socket;
		this.middlewares = middlewares;
		this.route = route;
		this.container = container;
	}
	
	@Override
	public void run() {
		try {
			// 构造 request 请求
			Request request = new RequestWrap(socket.getInputStream());
		
			// 得到输出流
			OutputStream output = socket.getOutputStream();
			
			// 路由
			Response response = this.route.route(request);
			
			// 如果响应为空
			if (response == null) {
				// 则生成一个 Not Found 响应
				response = new RsNotFound(request);
			}
			
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

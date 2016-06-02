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

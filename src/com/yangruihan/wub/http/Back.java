package com.yangruihan.wub.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.Route;

/**
 * 后端类
 * @author Yrh
 *
 */
public class Back {

	/**
	 * 路由
	 */
	private Route route;
	
	public Back(Route route) {
		this.route = route;
	}
	
	public void accept(Socket socket) throws IOException {
		// 得到 request
		Request request = new Request(socket.getInputStream());
		
		// 得到输出流
		OutputStream output = socket.getOutputStream();
		
		// 路由
		Response response = this.route.route(request);
		
		// 设置输出流
		response.setOutput(output);
		
		// 发送响应
		response.send();
		
		// 关闭 socket
		socket.close();
	}
}

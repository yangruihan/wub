package com.yangruihan.wub.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.yangruihan.wub.Middleware;
import com.yangruihan.wub.Route;
import com.yangruihan.wub.constant.Constant;
import com.yangruihan.wub.middleware.ContentLenMiddleware;
import com.yangruihan.wub.middleware.DateMiddleware;
import com.yangruihan.wub.middleware.ServerMiddleware;
import com.yangruihan.wub.route.RtRegex;

/**
 * 服务器
 * @author Yrh
 *
 */
public class Server {

	/**
	 * 服务端Socket
	 */
	private ServerSocket serverSocket;
	
	/**
	 * 后端
	 */
	private Container container;
	
	/**
	 * 中间件
	 */
	private List<Middleware> middlewares;
	
	/**
	 * Ctor.
	 * @param port
	 * @param route
	 * @throws IOException
	 */
	public Server(int port, Route route) throws IOException {
		this(port, new Container(route));
	}
	
	/**
	 * Ctor.
	 * @param port
	 * @param back
	 * @throws IOException
	 */
	public Server(int port, Container back) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.container = back;
		this.middlewares = new ArrayList<>();
		
		// 添加默认的中间件
		addDefaultMiddleware();
	}
	
	/**
	 * 添加默认的中间件
	 */
	private void addDefaultMiddleware() {
		this.middlewares.add(new DateMiddleware());
		this.middlewares.add(new ServerMiddleware());
		this.middlewares.add(new ContentLenMiddleware());
	}
	
	
	/**
	 * 添加中间件
	 * @param mid
	 * @return
	 */
	public Server addMiddleware(Middleware mid) {
		if (mid != null) {
			this.middlewares.add(mid);
		}
		return this;
	}
	
	/**
	 * 删除中间件
	 * @param mid
	 * @return
	 */
	public Server deleteMiddleware(Middleware mid) {
		if (mid != null) {
			this.middlewares.remove(mid);
		}
		return this;
	}
	
	/**
	 * 开启主循环
	 * @param exit
	 */
	public void start(int exit) throws IOException {
		this.serverSocket.setSoTimeout((int) TimeUnit.SECONDS.toMillis(1L));
		
		System.out.println("----- 服务器正在启动中 -----");
		System.out.println("----- 服务器启动完成 -----\n");
		
		try {
			do {
				this.loop(this.serverSocket);
			} while (exit == Constant.Exit.NEVER_EXIT ? true : false);
		} finally {
			this.serverSocket.close();
		}
	}
	
	private void loop(ServerSocket socket) throws IOException {
		try {
			this.container.accept(socket.accept(), this.middlewares);
		} catch (final SocketTimeoutException ex) {
            assert ex != null;
        }
	}
}

package com.yangruihan.wub.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.yangruihan.wub.constant.C;
import com.yangruihan.wub.middleware.CommonMiddleware;
import com.yangruihan.wub.middleware.Middleware;
import com.yangruihan.wub.middleware.ServerMiddleware;
import com.yangruihan.wub.route.Route;
import com.yangruihan.wub.route.RtRegex;
import com.yangruihan.wub.route.Urls;

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
	 * 容器
	 */
	private Container container;
	
	/**
	 * 中间件
	 */
	private List<Middleware> middlewares;
	
	/**
	 * 路由
	 */
	private Route route;
	
	/**
	 * Ctor.
	 * @throws IOException
	 */
	public Server() throws IOException {
		this(8080);
	}
	
	/**
	 * Ctor.
	 * @param port
	 * @throws IOException 
	 */
	public Server(int port) throws IOException {
		this(port, new RtRegex());
	}
	
	/**
	 * Ctor.
	 * @param port
	 * @param route
	 * @throws IOException
	 */
	private Server(int port, Route route) throws IOException {
		this(port, route, Container.getInstance());
	}
	
	/**
	 * Ctor.
	 * @param port
	 * @param back
	 * @throws IOException
	 */
	private Server(int port, Route route, Container container) throws IOException{
		this.serverSocket = new ServerSocket(port);
		this.route = route;
		this.container = container;
		this.middlewares = new ArrayList<>();
		
		// 添加默认的中间件
		addDefaultMiddleware();
	}
	
	/**
	 * 添加默认的中间件
	 */
	private void addDefaultMiddleware() {
		this.middlewares.add(new CommonMiddleware());
		this.middlewares.add(new ServerMiddleware());
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
	 * 添加Url映射
	 * @param urls
	 * @return
	 */
	public Server addUrls(Urls urls) {
		urls.addUrls(route);
		return this;
	}
	
	/**
	 * 设置路由类方法
	 * @param route
	 * @return
	 */
	public Server setRoute(Route route) {
		this.route = route;
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
			} while (exit == C.Exit.NEVER_EXIT ? true : false);
		} finally {
			this.serverSocket.close();
		}
	}
	
	private void loop(ServerSocket socket) throws IOException {
		try {
			// 启动一个请求处理线程
			new RequestHandlerThread(socket.accept(), middlewares, route, container).start();
		} catch (final SocketTimeoutException ex) {
            assert ex != null;
        }
	}
}

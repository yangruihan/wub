package com.yangruihan.wub.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import com.yangruihan.wub.Route;
import com.yangruihan.wub.constant.Constant;

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
	private Back back;
	
	/**
	 * Ctor.
	 * @param port
	 * @param back
	 * @throws IOException
	 */
	public Server(int port, Back back) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.back = back;
	}
	
	/**
	 * Ctor.
	 * @param port
	 * @param route
	 * @throws IOException
	 */
	public Server(int port, Route route) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.back = new Back(route);
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
			this.back.accept(socket.accept());
		} catch (final SocketTimeoutException ex) {
            assert ex != null;
        }
	}
}

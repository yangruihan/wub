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
	 * 单例模式
	 */
	private static Container instance;
	private static byte[] lock = new byte[1];
	
	/**
	 * 获得实例方法，线程安全的
	 * @return 实例
	 */
	public static Container getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new Container();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * Ctor.
	 * @param route
	 */
	private Container() {
	}
}

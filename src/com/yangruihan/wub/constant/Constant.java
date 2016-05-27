package com.yangruihan.wub.constant;

import java.io.File;

/**
 * 常量接口
 * @author Yrh
 *
 */
public interface Constant {

	/**
	 * 系统根目录
	 */
	static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "WEB-INF";
	
	/**
	 * 关于 HTTP 的常量
	 * @author Yrh
	 *
	 */
	interface Http {
		static final String HTTP_VERSION = "HTTP/1.1";
	}
	
	/**
	 * 关于退出的常量
	 * @author Yrh
	 *
	 */
	interface Exit {
		static final int NEVER_EXIT = 1;
		static final int ONCE_EXIT = 0;
	}
}

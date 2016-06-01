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
	
	/**
	 * 响应返回状态值
	 * @author YRH
	 *
	 */
	interface Response_status {
		/**
		 * 临时响应 1xx
		 */
		static final int CONTINUE = 100;
		static final int SWITCH_PROTOCOLS = 101;
		
		/**
		 * 成功 2xx
		 */
		static final int OK = 200;
		static final int CREATED = 201;
		static final int ACCEPTED = 202;
		static final int NON_AUTHORITATIVE_INFO = 203;
		static final int NO_CONTENT = 204;
		static final int RESET_CONTENT = 205;
		static final int PARTIAL_CONTENT = 206;
		
		/**
		 * 重定向 3xx
		 */
		static final int MULTIPLE_CHOICES = 300;
		static final int MOVED_PERMANENTLY = 301;
		static final int FOUND = 302;
		static final int SEE_OTHER = 303;
		static final int NOT_MODIFIED = 304;
		static final int USE_PROXY = 305;
		static final int TEMPORARY_REDIRECT = 307;
		
		/**
		 * 客户端请求错误 4xx
		 */
		static final int BAD_REQUEST = 400;
		static final int UNAUTHORIZED = 401;
		static final int PAYMENT_REQUIRED = 402;
		static final int FORBIDDEN = 403;
		static final int NOT_FOUND = 404;
		static final int METHOD_NOT_ALLOWED = 405;
		static final int NOT_ACCEPTABLE = 406;
		static final int PROXY_AUTHENTICATION_REQUIRED = 407;
		static final int REQUEST_TIMEOUT = 408;
		static final int CONFLICT = 409;
		static final int GONE = 410;
		static final int LENGTH_REQUIRED = 411;
		static final int PRECONDITION_FAILED = 412;
		static final int REQUEST_ENTITY_TOO_LARGE = 413;
		static final int REQUEST_URI_TOO_LONG = 414;
		static final int UNSUPPORTED_MEDIA_TYPE = 415;
		static final int REQUESTED_RANGE_NOT_SATISFIABLE = 416;
		static final int EXPECTATION_FAILED = 417;
		
		/**
		 * 服务器错误 5xx
		 */
		static final int INTERNAL_SERVER_ERROR = 500;
		static final int NOT_IMPLEMENTED = 501;
		static final int BAD_GATEWAY = 502;
		static final int SERVICE_UNAVAILABLE = 503;
		static final int GATEWAY_TIMEOUT = 504;
		static final int HTTP_VERSION_NOT_SUPPORTED = 505;
	}
}

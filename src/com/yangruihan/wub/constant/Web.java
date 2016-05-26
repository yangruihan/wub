package com.yangruihan.wub.constant;

import java.io.File;

public interface Web {

	static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "WEB-INF";
	static final String HTTP_VERSION = "HTTP/1.1";
	
	interface Exit {
		static final int NEVER_EXIT = 1;
	}
}

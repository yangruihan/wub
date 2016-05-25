package com.yangruihan.wub.constant;

import java.io.File;

public interface Web {

	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "WEB-INF";
	
	interface Exit {
		public static final int NEVER_EXIT = 1;
	}
}

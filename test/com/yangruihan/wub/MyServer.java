package com.yangruihan.wub;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.yangruihan.wub.action.CookieTest2Action;
import com.yangruihan.wub.action.CookieTestAction;
import com.yangruihan.wub.action.GetTestAction;
import com.yangruihan.wub.action.IndexAction;
import com.yangruihan.wub.action.JsonTestAction;
import com.yangruihan.wub.action.RsTextTestAction;
import com.yangruihan.wub.action.UserAction;
import com.yangruihan.wub.action.UserSubmitAction;
import com.yangruihan.wub.constant.C;
import com.yangruihan.wub.http.Server;
import com.yangruihan.wub.middleware.CommonMiddlewareTestAction;
import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.response.Response;
import com.yangruihan.wub.response.RsFile;
import com.yangruihan.wub.route.RegexTestAction;
import com.yangruihan.wub.route.Route;
import com.yangruihan.wub.route.RtRegex;

public class MyServer {

	public static void main(String[] args) throws IOException {
		new Server().addUrls(new MyUrls()).start(C.Exit.NEVER_EXIT);
//		new Server(8080).start(C.Exit.NEVER_EXIT);
	}
}

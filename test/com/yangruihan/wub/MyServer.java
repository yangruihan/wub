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
import com.yangruihan.wub.constant.Constant;
import com.yangruihan.wub.http.Server;
import com.yangruihan.wub.middleware.DateMiddlewareTestAction;
import com.yangruihan.wub.response.RsFile;
import com.yangruihan.wub.route.RegexTestAction;
import com.yangruihan.wub.route.RtRegex;

public class MyServer {

	public static void main(String[] args) throws IOException {
		
		Map<String, Action> maps = new HashMap<>();
		
		maps.put("^$", new IndexAction());
		maps.put("/user$", new UserAction());
		maps.put("/useraction$", new UserSubmitAction());
		maps.put("/get$", new GetTestAction());
		maps.put("/hello$", new RsTextTestAction());
		maps.put("/json$", new JsonTestAction());
		maps.put("/test$", new Action() {
			
			@Override
			public Response action(Request request) throws IOException {
				return new RsFile(request, "/test.txt");
			}
		});
		maps.put("/testCookie$", new CookieTestAction());
		maps.put("/testCookie2$", new CookieTest2Action());
		maps.put("/testDateMid$", new DateMiddlewareTestAction());
		maps.put("^/testRegex/(?P <userid>[0-9]+)/name/(?P<username>\\w+)/abc", new RegexTestAction());
		
		Server server = new Server(8080, new RtRegex(maps));
		
		server.start(Constant.Exit.NEVER_EXIT);
	}
}

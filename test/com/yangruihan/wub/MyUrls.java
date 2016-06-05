package com.yangruihan.wub;

import com.yangruihan.wub.action.CookieTest2Action;
import com.yangruihan.wub.action.CookieTestAction;
import com.yangruihan.wub.action.GetTestAction;
import com.yangruihan.wub.action.IndexAction;
import com.yangruihan.wub.action.JsonTestAction;
import com.yangruihan.wub.action.RsTextTestAction;
import com.yangruihan.wub.action.UserAction;
import com.yangruihan.wub.action.UserSubmitAction;
import com.yangruihan.wub.constant.C;
import com.yangruihan.wub.middleware.CommonMiddlewareTestAction;
import com.yangruihan.wub.route.RegexTestAction;
import com.yangruihan.wub.route.Route;
import com.yangruihan.wub.route.Urls;

public class MyUrls implements Urls {

	@Override
	public void addUrls(Route route) {
		route.addUrl("^$", new IndexAction());
		route.addUrl("/user", new UserAction());
		route.addUrl("/useraction$", new UserSubmitAction(), C.Http.POST);
		route.addUrl("/get", new GetTestAction());
		route.addUrl("/hello$", new RsTextTestAction());
		route.addUrl("/json$", new JsonTestAction());
		route.addUrl("/testCookie", new CookieTestAction());
		route.addUrl("/testCookie2", new CookieTest2Action());
		route.addUrl("/testDateMid$", new CommonMiddlewareTestAction());
		route.addUrl("^/testRegex/(?P <userid>[0-9]+)/name/(?P<username>\\w+)/abc", new RegexTestAction());
	}
}

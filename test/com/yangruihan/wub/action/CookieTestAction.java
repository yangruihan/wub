package com.yangruihan.wub.action;

import java.io.IOException;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.request.Cookie;
import com.yangruihan.wub.request.Cookie;
import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.response.Response;
import com.yangruihan.wub.response.RsText;

public class CookieTestAction implements Action {

	@Override
	public Response action(Request request) throws IOException {
		RsText rsText = new RsText(request, "Hello, World");
		Cookie cookie = new Cookie("test", "123");
		return rsText.addCookie(cookie).addCookie(new Cookie("test2", "456"));
	}

}

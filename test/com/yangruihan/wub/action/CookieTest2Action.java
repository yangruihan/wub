package com.yangruihan.wub.action;

import java.io.IOException;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.Cookie;
import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.response.RsText;

public class CookieTest2Action implements Action {

	@Override
	public Response action(Request request) throws IOException {
		Cookie cookie = request.getCookie("test");
		return new RsText(request, cookie.getValue());
	}

}

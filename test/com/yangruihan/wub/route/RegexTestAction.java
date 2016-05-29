package com.yangruihan.wub.route;

import java.io.IOException;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.response.RsNotFound;
import com.yangruihan.wub.response.RsText;

public class RegexTestAction implements Action {

	@Override
	public Response action(Request request) throws IOException {
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		
		return new RsText(request, userid+" " + username);
	}
}

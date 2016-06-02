package com.yangruihan.wub.middleware;

import java.io.IOException;
import java.util.Date;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.response.Response;
import com.yangruihan.wub.response.RsText;

public class DateMiddlewareTestAction implements Action {

	@Override
	public Response action(Request request) throws IOException {
		DateMiddleware dateMiddleware = new DateMiddleware();
		return dateMiddleware.processResponse(new RsText(request, new Date().toString()));
	}
	
}

package com.yangruihan.wub.action;

import java.io.IOException;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.response.RsText;

public class RsTextTestAction implements Action {

	@Override
	public Response action(Request request) throws IOException {
		return new RsText(request, "hello");
	}

}

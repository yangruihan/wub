package com.yangruihan.wub.action;

import java.io.IOException;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.response.RsFile;

public class JsonTestAction implements Action {

	@Override
	public Response action(Request request) throws IOException {
		return new RsFile(request, "/test.json", "text/json");
	}
}

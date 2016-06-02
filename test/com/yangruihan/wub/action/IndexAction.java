package com.yangruihan.wub.action;

import java.io.IOException;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.response.Response;
import com.yangruihan.wub.response.RsFile;

public class IndexAction implements Action {

	@Override
	public Response action(Request request) throws IOException {
		return new RsFile(request, "/index.html");
	}

}

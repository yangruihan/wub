package com.yangruihan.wub.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.response.RsText;
import com.yangruihan.wub.util.FileHelper;
import com.yangruihan.wub.view.TemplateView;

public class UserSubmitAction implements Action {

	@Override
	public Response action(Request request) throws IOException {
		System.out.println(request.getParameter("username"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", request.getParameter("username"));
		
		return new RsText(request, new TemplateView("/show", map).render());
	}

}

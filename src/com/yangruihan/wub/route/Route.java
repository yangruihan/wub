package com.yangruihan.wub.route;

import java.io.IOException;
import java.util.Map;

import com.yangruihan.wub.request.Request;
import com.yangruihan.wub.response.Response;

/**
 * 路由接口
 * @author Yrh
 *
 */
public interface Route {

	Response route(Request request) throws IOException;
}

package com.yangruihan.wub;

import java.io.IOException;
import java.util.Map;

/**
 * 路由接口
 * @author Yrh
 *
 */
public interface Route {

	Response route(Request request) throws IOException;
}

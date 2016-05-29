package com.yangruihan.wub.route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yangruihan.wub.Action;
import com.yangruihan.wub.Request;
import com.yangruihan.wub.Response;
import com.yangruihan.wub.response.RsNotFound;

/**
 * 正则表达式
 * 
 * @author YRH
 *
 */
public class RtRegex extends RouteWrap {

	/**
	 * Ctor.
	 * 
	 * @param maps
	 */
	public RtRegex(Map<String, Action> maps) {
		super(maps);
	}

	@Override
	public Response route(Request request) throws IOException {
		String uri = request.getUri().split("\\?")[0].trim();
		
		// 补全 uri
		if (!uri.endsWith("/")) {
			uri += "/";
		}

		Iterator<Map.Entry<String, Action>> iter = this.maps.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Action> entry = iter.next();

			String regexUrl = entry.getKey();

			/**
			 * 寻找用户自定义 Url 中的参数
			 */
			Pattern keyPattern = Pattern.compile("\\(\\?P\\s*<(.*?)>(.*?)\\)");
			Matcher matcher = keyPattern.matcher(regexUrl);

			if (matcher.find()) {
				// 参数表
				List<String> parameterNames = new ArrayList<>();
				// 参数对应正则表达式表
				List<String> parameterRegexs = new ArrayList<>();
				// 参数对应值表
				List<String> parameterValues = new ArrayList<>();

				do {
					String parameterName = matcher.group(1);
					String parameterRegex = matcher.group(2);

					parameterNames.add(parameterName);
					parameterRegexs.add(parameterRegex);
				} while (matcher.find());

				/**
				 * 寻找接收到的 Uri 中的参数
				 */
				String newRegexUrl = matcher.replaceAll("(.*?)");
				if (!newRegexUrl.endsWith("/") && !newRegexUrl.endsWith("$")) {
					newRegexUrl += "/";
				} else if (newRegexUrl.endsWith("$") && !newRegexUrl.endsWith("/$")) {
					newRegexUrl = newRegexUrl.substring(0, newRegexUrl.length() - 1) + "/$";
				}
				
				Pattern valuePattern = Pattern.compile(newRegexUrl);
				matcher = valuePattern.matcher(uri);
				if (matcher.find()) {
					for (int i = 1; i <= matcher.groupCount(); i++) {
						parameterValues.add(matcher.group(i));
					}
				}

				// 如果匹配成功
				if (parameterValues.size() > 0 && parameterNames.size() == parameterValues.size()
						&& parameterRegexs.size() == parameterNames.size()) {
					for (int i = 0; i < parameterValues.size(); i++) {
						// 如果第 i 个值 能和第 i 个正则表达式匹配上
						if (Pattern.matches(parameterRegexs.get(i), parameterValues.get(i))) {
							request.setParameter(parameterNames.get(i), parameterValues.get(i));
						}
					}

					// 并且执行该动作
					return entry.getValue().action(request);
				} else {
					continue;
				}
			} else {
				
				if (!regexUrl.endsWith("$")) {
					regexUrl += ".*";
				} else if (regexUrl.endsWith("$") && !regexUrl.endsWith("/$")) {
					regexUrl = regexUrl.substring(0, regexUrl.length() - 1) + "/$";
				}
				
				if (Pattern.matches(regexUrl, uri)) {
					return entry.getValue().action(request);
				} else {
					continue;
				}
			} // end of if
		}

		// 默认返回没有找到页面的响应
		return new RsNotFound(request);
	}
}

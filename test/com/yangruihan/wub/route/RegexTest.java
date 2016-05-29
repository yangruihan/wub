package com.yangruihan.wub.route;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		String url = "/user/(?P <userid>[0-9]+)/abc/(?P <username>[0-9]+)";
		
		Pattern keyPattern = Pattern.compile("\\(\\?P\\s*<(.*?)>(.*?)\\)");
		
		Matcher matcher = keyPattern.matcher(url);
		
		
		// 参数表
		List<String> parameterNames = new ArrayList<>();
		// 参数对应正则表达式表
		List<String> parameterRegexs = new ArrayList<>();
					
		String result = url;
		
		if (matcher.find()) {
			do {
				System.out.print(matcher.group(1));
				parameterNames.add(matcher.group(1));
				System.out.println("   " + matcher.group(2));
				parameterRegexs.add(matcher.group(2));
			} while (matcher.find());
			
			
			result = matcher.replaceAll("(.*?)");
			if (!result.endsWith("/") && !result.endsWith("$")) {
				result = result + "/";
			} else if (result.endsWith("$") && !result.endsWith("/$")) {
				result = result.substring(0, result.length() - 1) + "/$";
			}
		} else {
			if (!result.endsWith("$")) {
				result += ".*";
			}
		}
		
		System.out.println(result);
		
		Pattern valuePattern = Pattern.compile(result);
		matcher = valuePattern.matcher("/user/123/abc/yrh/");
		System.out.println(matcher.matches());
		System.out.println(matcher.group(1));
		System.out.println(matcher.group(2));
	}
}

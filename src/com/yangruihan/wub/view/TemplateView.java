package com.yangruihan.wub.view;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yangruihan.wub.util.FileHelper;

/**
 * 模板视图
 * @author Yrh
 *
 */
public class TemplateView implements View {

	/**
	 * 视图资源位置
	 */
	private String uri;
	
	/**
	 * 映射
	 */
	private Map<String, Object> map;
	
	/**
	 * Ctor.
	 * @param uri
	 */
	public TemplateView(String uri) {
		this.uri = uri;
	}
	
	/**
	 * Ctor.
	 * @param uri
	 * @param map
	 */
	public TemplateView(String uri, Map<String, Object> map) {
		this.uri = uri;
		this.map = map;
	}

	@Override
	public String render() throws IOException {
		if (uri == null || uri.isEmpty()) 
			return "";
		
		String content = FileHelper.getContent(uri);
		
		if (content == null) {
			// 找不到文件
			System.err.println("[Error] File not found: " + uri);
			String message = "HTTP/1.1 404 File NOT Fount\r\n" + "Content-Type: text/html\r\n"
					+ "Content-Length: 23\r\n\r\n<h1>File Not Found</h1>";
			return message;
		}
		
		if (map == null || map.size() == 0) 
			return content;
		
		/**
		 * 替换中间值
		 */
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			
			String key = entry.getKey();
			Object value = entry.getValue();
			
			Pattern pattern = Pattern.compile("\\{" + key + "\\}");
			Matcher result = pattern.matcher(content);
			content = result.replaceAll(value == null ? "" : value.toString());
		}
		
		return content;
	}
}

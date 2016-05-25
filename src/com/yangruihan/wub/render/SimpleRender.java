package com.yangruihan.wub.render;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简单渲染器
 * @author Yrh
 *
 */
public class SimpleRender {

	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * Ctor.
	 * @param content
	 */
	public SimpleRender(String content) {
		this.content = content;
	}
	
	/**
	 * 渲染
	 * @param map
	 * @return
	 */
	public String render(Map<String, Object> map) {
		
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			
			String key = entry.getKey();
			Object value = entry.getValue();
			
			Pattern pattern = Pattern.compile("\\{" + key + "\\}");
			Matcher result = pattern.matcher(this.content);
			this.content = result.replaceAll(value == null ? "" : value.toString());
		}
		
		return this.content;
	}
}

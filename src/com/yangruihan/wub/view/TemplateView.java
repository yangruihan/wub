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

    // regex to match variable
	private String variable = "\\{\\{ %s \\}\\}";

    // regex to match comment
    private String comment = "\\{# .*? #\\}";

    //regex to match tag
    private String tagName = "\\{% .*? %\\}";

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
			return null;
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

            Pattern pattern = Pattern.compile(String.format(variable, key));

            Matcher result = pattern.matcher(content);
			content = result.replaceAll(value == null ? "" : value.toString());
		}

        // remove comment
        Pattern pattern = Pattern.compile(comment);
        Matcher result = pattern.matcher(content);
        content = result.replaceAll("");


        // render if
        pattern = Pattern.compile(tagName);
        result = pattern.matcher(content);
        System.out.println(result.toString());

        return content;
	}
}

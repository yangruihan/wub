package com.yangruihan.wub.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.yangruihan.wub.constant.Constant;

/**
 * 获取HTML文件
 * @author Yrh
 *
 */
public class FileHelper {

	/**
	 * 通过 uri 得到一个文件的内容
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	public static String getContent(String uri) throws IOException {
		File file = new File(Constant.WEB_ROOT, uri);
		BufferedReader reader = null;
		if (file.exists()) {
			reader = new BufferedReader(new FileReader(file));
			StringBuffer buffer = new StringBuffer();
			String temp;
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp + "\n");
			}
			reader.close();
			return buffer.toString();
		} else {
			return null;
		}
	}
}

package com.yangruihan.wub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class App {
	
	/**
	 * 响应头
	 */
	private static final String RES_HEADER_HTML = "HTTP/1.1 200 OK\r\nContent-type: text/html\r\n\r\n";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		System.out.println("-----启动服务器中-----");
		
		final ServerSocket server = new ServerSocket(8080);
		
		System.out.println("-----启动服务器完成-----");
		
		while (true) {
			
			Socket socket = server.accept();
			
			final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			final List<String> header = new LinkedList<>();
			final List<String> body = new LinkedList<>();
			
			System.out.println("-----接受到一个 request-----");
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				System.out.println("--- reader.ready: " + reader.ready());
				System.out.println("--- line.isEmpty: " + line.isEmpty());
				System.out.println("--- line length: " + line.length());
				
				if (line.isEmpty()) {
					
					// 接受body
					if (reader.ready()) {
						BufferedReader reader2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String line2 = null;
						while (!(line2 = reader2.readLine()).isEmpty()) {
							System.out.println(line2);
						}
					}
					
					System.out.println("--- 进入isEmpty");
					
					String response = RES_HEADER_HTML + simpleRoute(header.get(0));
					writer.append(response);
					writer.flush();
					break;
				} 
				
				header.add(line);
				System.out.println(line);
			}
			
//			char[] buf = new char[512];
//			int len;
//			StringBuffer sb = new StringBuffer();
//			while ((len = reader.read(buf)) != -1) {
//				String str = new String(buf, 0, len);
//				sb.append(str);
//				System.out.println(str);
//			}
//			
//			System.out.println("---");
//			
//			for (String str : sb.toString().split("\r\n")) {
//				header.add(str);
//			}
			
//			String response = RES_HEADER_HTML + simpleRoute(header.get(0));
//			writer.append(response);
//			writer.flush();
			
			System.out.println("--------------------\n\n");
			
			reader.close();
			writer.close();
			socket.close();
		}
	}
	
	/**
	 * 简单路由
	 * @param url 匹配url
	 * @return response body
	 * @throws IOException
	 */
	private static String simpleRoute(String url) throws IOException {
		String parsedUrl = url.split(" ")[1];
		String response_body = "";
		switch (parsedUrl) {
		case "/":
			response_body = renderHtml("res/index.html");
			break;
		case "/hello":
			response_body = "Hello, World!";
			break;
		case "/user":
			response_body =	renderHtml("res/user.html");
			break;
		default:
			response_body = "This is default page";
			break;
		}
		return response_body + "\r\n";
	}
	
	/**
	 * HTML 渲染
	 * @param uri HTML 资源
	 * @return
	 * @throws IOException
	 */
	private static String renderHtml(String uri) throws IOException {
		StringBuffer ret = new StringBuffer();
		File file = new File(uri);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String temp = null;
		
		while ((temp = reader.readLine()) != null) {
			ret.append(temp + "\r\n");
		}
		
		reader.close();
		return ret.toString();
	}
}

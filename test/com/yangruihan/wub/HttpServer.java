package com.yangruihan.wub;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class HttpServer {

	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "res";
	
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

	private boolean shutdown = false;

	public static void main(String[] args) {
		HttpServer server = new HttpServer();  
        server.await();  
	}

	public void await() {

		ServerSocket serverSocket = null;

		int port = 8080;

		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				
				RequestTest request = new RequestTest(input);
				
				ResponseTest response = new ResponseTest(output);
				response.setRequest(request);
				
				route(response);
				
				socket.close();
				
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	
	private void route(ResponseTest response) throws IOException {
		String uri = response.getRequest().getUri();
		
		if (uri.endsWith(".html")) {
			response.sendStaticResource(uri);
		} else {
			simpleRoute(response, uri);
		}
	}
	
	private void simpleRoute(ResponseTest response, String uri) throws IOException {
		switch (uri) {
		case "/":
			response.sendStaticResource("/index.html");
			break;
		case "/hello":
			response.sendResponse("HTTP/1.1 200 OK\r\nContent-type: text/html\r\n", "Hello World!");
			break;
		case "/user":
			response.sendStaticResource("/user.html");
			break;
		default:
			response.sendResponse("HTTP/1.1 200 OK\r\nContent-type: text/html\r\n", "This is default page");
			break;
		}
	}
}

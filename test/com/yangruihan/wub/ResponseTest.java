package com.yangruihan.wub;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ResponseTest {

	private static final int BUFFER_SIZE = 1024;
	
	private RequestTest request;
	private OutputStream output;
	
	public ResponseTest(OutputStream output) {
		this.output = output;
	}
	
	public void setRequest(RequestTest request) {
		this.request = request;
	}
	
	public RequestTest getRequest() {
		return this.request;
	}

	public void sendStaticResource(String uri) throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		
		File file = new File(HttpServer.WEB_ROOT, uri);
		
		if (file.exists()) {
			
			fis = new FileInputStream(file);
			int ch = fis.read(bytes, 0, BUFFER_SIZE);
			
			while (ch != -1) {
				output.write(bytes, 0, ch);
				ch = fis.read(bytes, 0, BUFFER_SIZE);
			}
		} else {
			// file not found  
            String errorMessage = "HTTP/1.1 404 File NOT Fount\r\n" +   
            "Content-Type: text/html\r\n" +   
            "Content-Length: 23\r\n" +  
            "\r\n" +   
            "<h1>File Not Found</h1>";   
            output.write(errorMessage.getBytes());  
		}
		
		output.flush();
		
		if (fis != null) {
			fis.close();
		}
	}
	
	public void sendResponse(String header, String body) {
		String response = header + "\r\n" + body + "\r\n";
		
		try {
			this.output.write(response.getBytes());
			this.output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

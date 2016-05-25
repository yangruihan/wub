package com.yangruihan.wub;

public class A {

	public void send() {
		a();
		b();
		
		System.out.println("A.send");
	}
	
	public void a() {
		System.out.println("A.a");
	}
	
	public void b() {
		System.out.println("A.b");
	}
}

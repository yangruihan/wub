package com.yangruihan.wub;

public class B extends A {

	@Override
	public void send() {
		super.send();
	}
	
	@Override
	public void a() {
		System.out.println("B.a");
	}
	
	@Override
	public void b() {
		System.out.println("B.b");
	}
	
	public static void main(String[] args) {
		A b = new B();
		b.send();
	}
}

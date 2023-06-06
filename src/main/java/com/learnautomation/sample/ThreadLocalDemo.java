package com.learnautomation.sample;

public class ThreadLocalDemo {

	public static void main(String[] args) {

		
		ThreadLocal<Integer> tl1=new ThreadLocal<Integer>();
		
		tl1.set(89);
		
		System.out.println(tl1.get());
		
		ThreadLocal<Integer> tl2=new ThreadLocal<Integer>();
		
		tl2.set(890);
		
		System.out.println(tl2.get());
		
		ThreadLocal<String> tl3=new ThreadLocal<String>();
		
		tl3.set("Bansh");
		
		System.out.println(tl3.get());

	}

}

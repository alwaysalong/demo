package com.demo.server.impl;

import com.demo.server.DemoService;


public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		System.out.println("---- name: "+name);
		return "hello,"+name;
	}

}

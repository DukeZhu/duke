package com.junicorn.duke.route;

import java.lang.reflect.Method;

/*
 * 路由
 * @author zhujh
 * */
public class Route {


	private String path;//路由路径
	
	private Method action; //执行路由的方法
	
	private Object controller; //路由所在的控制器
	
	public Route(){
		
	}
	
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Method getAction() {
		return action;
	}

	public void setAction(Method action) {
		this.action = action;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

}

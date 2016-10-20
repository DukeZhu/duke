package com.junicorn.duke.route;

import java.util.List;

/**
 * 路由匹配器，用于匹配路由
 * @author zhujh
 */
public class RouteMatcher {

	private List<Route> routes;
	
	public RouteMatcher(List<Route> routes){
		this.routes = routes;
	}
	
	public void setRoutes(List<Route> routes){
		this.routes = routes;
	}
	
	
}

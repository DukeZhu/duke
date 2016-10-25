package com.junicorn.duke;

import java.lang.reflect.Method;

import com.junicorn.duke.config.ConfigLoader;
import com.junicorn.duke.render.JspRender;
import com.junicorn.duke.render.Render;
import com.junicorn.duke.route.Routers;
import com.junicorn.duke.servlet.wrapper.Request;
import com.junicorn.duke.servlet.wrapper.Response;

public class Duke {

	private Routers routers;
	
	public Routers getRouters() {
		return routers;
	}

	public void setRouters(Routers routers) {
		this.routers = routers;
	}

	public ConfigLoader getConfigloader() {
		return configloader;
	}

	public void setConfigloader(ConfigLoader configloader) {
		this.configloader = configloader;
	}

	public boolean isInitial() {
		return initial;
	}

	public void setInitial(boolean initial) {
		this.initial = initial;
	}

	public Render getRender() {
		return render;
	}

	public void setRender(Render render) {
		this.render = render;
	}

	private ConfigLoader configloader;
	
	private boolean initial = false;
	
	private Render render;
	
	public boolean isInit(){
		return initial;
	}
	
	private Duke(){
		routers = new Routers();
		configloader = new ConfigLoader();
		render = new JspRender();
	}
	
	
	private static class DukeHolder {
		private static Duke ME = new Duke();
	}
	
	public static Duke me(){
		return DukeHolder.ME;
	}
	
	public Duke loadConf(String conf){
		configloader.load(conf);
		return this;
	}
	public Duke setConf(String name, String value){
		configloader.setConf(name, value);
		return this;		 
	}
	public String getConf(String name){
		return configloader.getConf(name);
	}
	
	public Duke addRoutes(Routers routers){
		this.routers.addRoute(routers.getRoutes());
		return this;
	}
	
	/**
	 * 添加路由
	 * @param path			映射的PATH
	 * @param methodName	方法名称
	 * @param controller	控制器对象
	 * @return				返回Mario
	 */
	public Duke addRoute(String path, String methodName, Object controller){
		try {
			Method method = controller.getClass().getMethod(methodName, Request.class, Response.class);
			this.routers.addRoute(path, method, controller);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return this;
	}
	
}

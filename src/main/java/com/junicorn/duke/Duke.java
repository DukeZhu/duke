package com.junicorn.duke;

import com.junicorn.duke.config.ConfigLoader;
import com.junicorn.duke.render.Render;
import com.junicorn.duke.route.Routers;

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
		render = new Render();
	}
	
	
	private static class DukeHolder {
		private static Duke ME = new Duke();
	}
	
	public static Duke me(){
		return DukeHolder.ME;
	}
	
}

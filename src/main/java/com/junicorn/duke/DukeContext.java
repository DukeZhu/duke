package com.junicorn.duke;

import javax.servlet.ServletContext;

import com.junicorn.duke.servlet.wrapper.Request;
import com.junicorn.duke.servlet.wrapper.Response;

public final class DukeContext {

	private static final ThreadLocal<DukeContext> CONTEXT = new ThreadLocal<DukeContext>();
	
	
	private ServletContext context;
	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	private Request request;
	private Response response;
	
	public static DukeContext me(){
		return CONTEXT.get();
	}
	
	private DukeContext() {
	}
	
	 public static void remove(){
	    	CONTEXT.remove();
	}
	public static void initContext(ServletContext context, Request request, Response response) {
		    DukeContext dukeContext = new DukeContext();
		    dukeContext.context = context;
		    dukeContext.request = request;
		    dukeContext.response = response;
	    	CONTEXT.set(dukeContext);
	}
	
}

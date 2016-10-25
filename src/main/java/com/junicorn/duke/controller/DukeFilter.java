package com.junicorn.duke.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.junicorn.duke.Duke;
import com.junicorn.duke.route.Route;
import com.junicorn.duke.route.RouteMatcher;
import com.junicorn.duke.route.Routers;

public class DukeFilter implements Filter{

	private static final Logger loggger = Logger.getLogger(DukeFilter.class.getName());

	private RouteMatcher routeMatcher = new RouteMatcher(new ArrayList<Route>());
	
	private ServletContext servletContext;
	
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Duke duke = Duke.me();
        if(!duke.isInit()){

            String className = filterConfig.getInitParameter("bootstrap");
            Bootstrap bootstrap = this.getBootstrap(className);
            bootstrap.init(duke);

            Routers routers = duke.getRouters();
            if(null != routers){
                routeMatcher.setRoutes(routers.getRoutes());
            }
            servletContext = filterConfig.getServletContext();

            duke.setInitial(true);
        }
	}
	
	 private Bootstrap getBootstrap(String className) {
	        if(null != className){
	            try {
	                Class<?> clazz = Class.forName(className);
	                Bootstrap bootstrap = (Bootstrap) clazz.newInstance();
	                return bootstrap;
	            } catch (ClassNotFoundException e) {
	                throw new RuntimeException(e);
	            } catch (InstantiationException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	        throw new RuntimeException("init bootstrap class error!");
	    }

}

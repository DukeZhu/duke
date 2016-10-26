package com.junicorn.duke.render;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.junicorn.duke.Const;
import com.junicorn.duke.Duke;
import com.junicorn.duke.DukeContext;

public class JspRender implements Render{

	public void render(String view, Writer writer) {

        String viewPath = this.getViewPath(view);

        HttpServletRequest servletRequest = DukeContext.me().getRequest().getRaw();
        HttpServletResponse servletResponse = DukeContext.me().getResponse().getRaw();
        try {
            servletRequest.getRequestDispatcher(viewPath).forward(servletRequest, servletResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getViewPath(String view){
        Duke duke = Duke.me();
        String viewPrfix = duke.getConf(Const.VIEW_PREFIX_FIELD);
        String viewSuffix = duke.getConf(Const.VIEW_SUFFIX_FIELD);

        if (null == viewSuffix || viewSuffix.equals("")) {
            viewSuffix = Const.VIEW_SUFFIX;
        }
        
        if (null == viewPrfix || viewPrfix.equals("")) {
            viewPrfix = Const.VIEW_PREFIX;
        }
        String viewPath = viewPrfix + "/" + view;
        if (!view.endsWith(viewSuffix)) {
            viewPath += viewSuffix;
        }
        return viewPath.replaceAll("[/]+", "/");
    }

}

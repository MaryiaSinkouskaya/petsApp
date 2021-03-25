package com.leverx.app.config.odata;

import org.apache.olingo.odata2.core.servlet.ODataServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "appServlet",
        initParams = {@WebInitParam(name = "org.apache.olingo.odata2.service.factory",
                value = "com.leverx.app.config.odata.ODataJPAFactoryImplApp")},
        urlPatterns = "/odata/*", loadOnStartup = 1)
public class AppServlet extends ODataServlet {
}
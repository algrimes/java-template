package com.testupstream.app.bundles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testupstream.app.AppConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigServlet extends HttpServlet {

    private AppConfig configuration;

    public ConfigServlet(AppConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(new ObjectMapper().writeValueAsString(configuration));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);

    }
}

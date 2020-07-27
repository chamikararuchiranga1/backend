package com.mvc.controller;

import com.mvc.dao.FeatureDao;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;


@WebServlet(urlPatterns = "/feature")
public class FeatureServlet extends HttpServlet {
    public FeatureServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String projectId = request.getParameter("projectId");
        JsonArrayBuilder featureArray = Json.createArrayBuilder();

        FeatureDao dao = new FeatureDao();

        try {
            ResultSet rst = dao.allFeatures(projectId);

            while (rst.next()){
                JsonObjectBuilder array = Json.createObjectBuilder();
                array.add("featureId", rst.getInt(1));
                array.add("feature", rst.getString(2));

                featureArray.add(array.build());
            }

            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(featureArray.build());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String featureId = request.getParameter("featureId");

        System.out.println(featureId);

        FeatureDao dao = new FeatureDao();

        try {
            boolean isDelete = dao.delete(featureId);

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.getWriter().print(isDelete);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String projectId = request.getParameter("projectId");
        String feature = request.getParameter("feature");

        FeatureDao dao = new FeatureDao();

        try {

            boolean issAdded = dao.update(projectId, feature);

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.getWriter().print(issAdded);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

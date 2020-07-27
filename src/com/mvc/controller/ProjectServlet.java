package com.mvc.controller;

import com.google.gson.Gson;
import com.mvc.bean.ProjectBean;
import com.mvc.dao.FeatureDao;
import com.mvc.dao.ProjectDao;

import javax.json.*;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(urlPatterns = "/project")
public class ProjectServlet extends HttpServlet {
    public ProjectServlet(){
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonArrayBuilder projectArray = Json.createArrayBuilder();
        int userId = Integer.parseInt(request.getParameter("userId"));
        String state = request.getParameter("state");

        ProjectDao projectDao = new ProjectDao();

        try {
            ResultSet rst = projectDao.allProject(userId, state);

            while (rst.next()){
                JsonObjectBuilder project = Json.createObjectBuilder();
                project.add("projectId", rst.getInt(1));
                project.add("projectName", rst.getString(2));
                projectArray.add(project.build());
            }

            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(projectArray.build());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuffer jb = new StringBuffer();
        String line = null;

        try {

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);


            ProjectBean projectBean = new Gson().fromJson(jb.toString(), ProjectBean.class);

            ProjectDao dao = new ProjectDao();
            int id = dao.addProject(projectBean.getProjectName());

            boolean isAdded=false;

            if (id > 0){
                FeatureDao featureDao = new FeatureDao();
                isAdded = featureDao.addFeature(projectBean.getFeatures(), id);
            }

            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

            response.getWriter().print(isAdded);

        } catch (Exception e) {
            e.printStackTrace();
            /*report an error*/ }

    }

}

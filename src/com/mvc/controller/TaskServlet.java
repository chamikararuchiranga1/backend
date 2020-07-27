package com.mvc.controller;

import com.google.gson.Gson;
import com.mvc.bean.FilterDataBean;
import com.mvc.bean.TaskBean;
import com.mvc.dao.TaskDao;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;


@WebServlet(urlPatterns = "/task")
public class TaskServlet extends HttpServlet {
    public TaskServlet(){
    }

//    @Path("task/fiter");
//    protected void doFIlter(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
//
//    }

    protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {

        TaskBean bean = new TaskBean();
        bean.setProjectId(request.getParameter("projectId"));
        bean.setEmployeeId(request.getParameter("employeeId"));
        bean.setMainFeature(request.getParameter("mainFeature"));
        bean.setTaskDetail(request.getParameter("taskDetail"));
        bean.setDate(request.getParameter("date"));
        bean.setTaskType(request.getParameter("taskType"));
        bean.setNumberOfHours(request.getParameter("numberOfHours"));
        bean.setComments(request.getParameter("comments"));


        TaskDao taskDao = new TaskDao();

        try {
            boolean isAdded = taskDao.addTask(bean);

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(isAdded);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonArrayBuilder taskArray = Json.createArrayBuilder();
        StringBuffer jb = new StringBuffer();
        String line = null;

        TaskDao taskDao = new TaskDao();

        try {

            BufferedReader reader = request.getReader();

            while ((line = reader.readLine()) != null)
                jb.append(line);

            FilterDataBean dataBean = new Gson().fromJson(jb.toString(), FilterDataBean.class);

            ResultSet rst = taskDao.getAllTask(dataBean);

            while (rst.next()){
                JsonObjectBuilder task = Json.createObjectBuilder();

                task.add("taskId", rst.getInt(1));
                task.add("employeeId", rst.getInt(2));
                task.add("projectId", rst.getInt(3));
                task.add("firstName", rst.getString(4));
                task.add("lastName", rst.getString(5));
                task.add("projectName", rst.getString(6));
                task.add("designation", rst.getString(7));
                task.add("mainFeature", rst.getString(8));
                task.add("taskDetail", rst.getString(9));
                task.add("date", rst.getString(10));
                task.add("taskType", rst.getString(11));
                task.add("numberOfHours", rst.getInt(12));
                task.add("comments", rst.getString(13));

                taskArray.add(task.build());

            }

            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(taskArray.build());

        }catch (Exception e){
            e.printStackTrace();
        }





//        JsonArrayBuilder taskArray = Json.createArrayBuilder();
//
//        TaskDao taskDao = new TaskDao();
//
//        try {
//            ResultSet rst = taskDao.getAllTask();
//
//            while (rst.next()){
//                JsonObjectBuilder task = Json.createObjectBuilder();
//
//                task.add("taskId", rst.getInt(1));
//                task.add("employeeId", rst.getInt(2));
//                task.add("projectId", rst.getInt(3));
//                task.add("firstName", rst.getString(4));
//                task.add("lastName", rst.getString(5));
//                task.add("projectName", rst.getString(6));
//                task.add("designation", rst.getString(7));
//                task.add("mainFeature", rst.getString(8));
//                task.add("taskDetail", rst.getString(9));
//                task.add("date", rst.getString(10));
//                task.add("taskType", rst.getString(11));
//                task.add("numberOfHours", rst.getInt(12));
//                task.add("comments", rst.getString(13));
//
//                taskArray.add(task.build());
//
//            }
//
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//            response.getWriter().print(taskArray.build());
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
    };
}

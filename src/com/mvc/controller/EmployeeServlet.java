package com.mvc.controller;

import javax.json.*;
import com.mvc.dao.EmployeeDao;
import com.mvc.dao.EmployeeWorkDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet (urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    public EmployeeServlet(){
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String projectId = request.getParameter("projectId");
        String user = request.getParameter("user");
        String designation = request.getParameter("designation");

        EmployeeDao dao = new EmployeeDao();

        try {
            int id = dao.add(user,designation);

            boolean isAdded=false;

            if (id > 0){
                EmployeeWorkDao workDao = new EmployeeWorkDao();
                isAdded = workDao.addEmployee(projectId,id);

            }

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.getWriter().print(isAdded);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonArrayBuilder employeeArray = Json.createArrayBuilder();

        EmployeeDao employeeDao = new EmployeeDao();

        try {
            ResultSet rst = employeeDao.allEmployee();

            while (rst.next()){
                JsonObjectBuilder employee = Json.createObjectBuilder();
                employee.add("employeeId", rst.getInt(1));
                employee.add("userId", rst.getInt(2));
                employee.add("firstName", rst.getString(3));
                employee.add("lastName", rst.getString(4));
                employee.add("designation", rst.getString(5));
                employeeArray.add(employee.build());
            }
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(employeeArray.build());

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String employeeId = request.getParameter("employeeId");
        String state = request.getParameter("state");

        System.out.println(employeeId);

        EmployeeDao dao = new EmployeeDao();

        try {

            boolean added = dao.update(employeeId,state);

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.getWriter().print(added);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}

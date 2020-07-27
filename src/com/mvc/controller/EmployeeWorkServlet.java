package com.mvc.controller;

import com.mvc.dao.EmployeeWorkDao;

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

@WebServlet(urlPatterns = "/employee-work")
public class EmployeeWorkServlet extends HttpServlet {

    public EmployeeWorkServlet(){
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("name");
        JsonArrayBuilder employeeWorkArray = Json.createArrayBuilder();

        EmployeeWorkDao employeeWorkDao = new EmployeeWorkDao();

        try {
            ResultSet rst = employeeWorkDao.allWorkEmployee(userId);

            while (rst.next()){
                JsonObjectBuilder employeeWork = Json.createObjectBuilder();
                employeeWork.add("projectId", rst.getInt(1));
                employeeWork.add("employeeId", rst.getInt(2));
                employeeWork.add("projectName", rst.getString(3));
                employeeWork.add("designation", rst.getString(4));

                employeeWorkArray.add(employeeWork.build());
            }
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(employeeWorkArray.build());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

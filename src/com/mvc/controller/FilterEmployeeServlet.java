package com.mvc.controller;

import com.mvc.dao.FilterEmployeeDao;

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

@WebServlet(urlPatterns = "/filter-employee")
public class FilterEmployeeServlet extends HttpServlet {
    public FilterEmployeeServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String projectId = request.getParameter("projectId");
        JsonArrayBuilder filterArray = Json.createArrayBuilder();

        FilterEmployeeDao employeeDao = new FilterEmployeeDao();

        try {
            ResultSet rst = employeeDao.allFilterEmployee(projectId);
            while (rst.next()){
                JsonObjectBuilder array = Json.createObjectBuilder();
                array.add("employeeId", rst.getInt(1));
                array.add("userId", rst.getInt(2));
                array.add("designation", rst.getString(3));
                array.add("firstName", rst.getString(4));
                array.add("lastName", rst.getString(5));
                array.add("status", rst.getString(6));

                filterArray.add(array.build());
            }

            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(filterArray.build());

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}

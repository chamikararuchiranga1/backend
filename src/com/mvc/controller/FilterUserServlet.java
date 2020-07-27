package com.mvc.controller;

import com.mvc.dao.FIlterUserDao;

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

@WebServlet(urlPatterns = "/filterUser")
public class FilterUserServlet extends HttpServlet {

    public FilterUserServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonArrayBuilder array = Json.createArrayBuilder();
        String projectId = request.getParameter("projectId");

        FIlterUserDao userDao = new FIlterUserDao();

        try {
             ResultSet rst = userDao.filterEmployee(projectId);

            while (rst.next()){
                JsonObjectBuilder user = Json.createObjectBuilder();
                user.add("userId", rst.getInt(1));
                user.add("firstName", rst.getString(2));
                user.add("lastName", rst.getString(3));

                array.add(user.build());

            }

            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(array.build());


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

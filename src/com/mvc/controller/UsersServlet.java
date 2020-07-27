package com.mvc.controller;

import com.mvc.bean.UserBean;
import com.mvc.dao.UsersDao;

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

@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    public UsersServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        JsonArrayBuilder allUsers = Json.createArrayBuilder();
        UsersDao dao = new UsersDao();
        try {
            ResultSet rst = dao.getAllUsers();

            while (rst.next()){
                JsonObjectBuilder user = Json.createObjectBuilder();
                user.add("user_id", rst.getInt(1));
                user.add("user_name", rst.getString(2));
                user.add("first_name", rst.getString(3));
                user.add("last_name", rst.getString(4));
                allUsers.add(user.build());
            }

            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(allUsers.build());

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserBean user = new UserBean();
        user.setFristName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setUserRole("EMPLOYEE");

        UsersDao dao = new UsersDao();

        try {
            boolean isAdded = dao.addUser(user);

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");

            response.getWriter().print(isAdded);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

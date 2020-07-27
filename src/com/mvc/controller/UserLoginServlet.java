package com.mvc.controller;


import com.mvc.bean.UserBean;
import com.mvc.dao.UserLoginDao;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet {
    public UserLoginServlet(){
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserBean bean = new UserBean();
        String typePass = request.getParameter("password");
        System.out.println(request.getParameter("password"));
        bean.setUserName(request.getParameter("username"));
        bean.setPassword(request.getParameter("password"));

        UserLoginDao userLoginDao = new UserLoginDao();
        try {
            UserBean userBean = userLoginDao.userLogin(bean);

            JsonObjectBuilder user = Json.createObjectBuilder();
            if (userBean.getUserId()!=null){
                user.add("userId", userBean.getUserId());
                user.add("userName", userBean.getUserName());
                user.add("userRole", userBean.getUserRole());
                user.add("firstName", userBean.getFristName());
                user.add("lastName", userBean.getLastName());

                String getPass = userBean.getPassword();

                if (getPass.equals(typePass)){
                    user.add("state", true);
                    user.add("massage", "User Name and Password Correct!");
                }else {
                    user.add("state", false);
                    user.add("massage", "Password Invalid!");
                }

            }else {
                user.add("state", false);
                user.add("massage", "User Name Invalid!");
            }
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().print(user.build());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    };
}

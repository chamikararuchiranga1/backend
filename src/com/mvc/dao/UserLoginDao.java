package com.mvc.dao;

import com.mvc.bean.UserBean;
import com.mvc.crud.Crud;

import java.sql.Array;
import java.sql.ResultSet;

public class UserLoginDao {

    public UserBean userLogin(UserBean bean) throws Exception , NullPointerException {

        ResultSet rst = Crud.executeQuery("select * from user_login where user_login.user_name=?", bean.getUserName());
        UserBean userBean = new UserBean();
        while (rst.next()){
            userBean.setUserId(rst.getInt(1)+"");
            userBean.setUserName(rst.getString(2));
            userBean.setPassword(rst.getString(3));
            userBean.setUserRole(rst.getString(4));
            userBean.setFristName(rst.getString(5));
            userBean.setLastName(rst.getString(6));
        }

        return userBean;

    };
}

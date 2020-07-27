package com.mvc.dao;

import com.mvc.bean.UserBean;
import com.mvc.crud.Crud;
import java.sql.ResultSet;

public class UsersDao {

    public ResultSet getAllUsers() throws Exception{

        ResultSet rst = Crud.executeQuery("select user_id, user_name, first_name, last_name from User_login where user_role='EMPLOYEE'");

        return rst;
    }

    public boolean addUser(UserBean bean) throws Exception{

        int i = Crud.executeUpdate("INSERT INTO user_login(user_name, password , user_role , first_name , last_name) VALUES (?,?,?,?,?)" ,bean.getUserName(), bean.getPassword(), bean.getUserRole(), bean.getFristName(), bean.getLastName());

        if (i>0){
            return true;
        }
        return false;

    }
}

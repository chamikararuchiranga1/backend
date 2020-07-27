package com.mvc.dao;

import com.mvc.crud.Crud;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.sql.Array;
import java.sql.ResultSet;

public class FIlterUserDao {

    public ResultSet filterEmployee(String id) throws Exception{

        ResultSet rst = Crud.executeQuery("select user_login.user_id, user_login.first_name, user_login.last_name from user_login where user_login.user_id not in (select employee.user_id from project, employee, employee_work where project.project_id=employee_work.project_id && employee_work.employee_id=employee.employee_id && project.project_id=?) && user_login.user_role = 'EMPLOYEE' group by user_login.user_id",id);

        return rst;
    }
}

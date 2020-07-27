package com.mvc.dao;

import com.mvc.crud.Crud;

import java.sql.ResultSet;

public class FilterEmployeeDao {

    public ResultSet allFilterEmployee(String projectId) throws Exception{
        ResultSet rst = Crud.executeQuery("select employee.employee_id, user_login.user_id, employee.designation, user_login.first_name,user_login.last_name, employee.status from project, employee_work, employee, user_login where project.project_id=employee_work.project_id && employee_work.employee_id=employee.employee_id && employee.user_id=user_login.user_id && project.project_id=?",projectId);

        return rst;
    };
}

package com.mvc.dao;

import com.mvc.crud.Crud;

import java.sql.ResultSet;

public class EmployeeWorkDao {

    public ResultSet allWorkEmployee(String userId) throws Exception{
        ResultSet rst = Crud.executeQuery(
                "select project.project_id, employee.employee_id, project.project_name, employee.designation from project,user_login,employee,employee_work where user_login.user_id=employee.user_id && employee.employee_id=employee_work.employee_id && employee_work.project_id=project.project_id && employee.status='ACTIVE' && user_login.user_id=?",userId);

        return rst;

    }


    public boolean addEmployee(String projectId, int employeeId)throws Exception{

        int i = Crud.executeUpdate("INSERT INTO employee_work VALUES (?,?)",projectId,employeeId);

        if (i>0){
            return true;
        }else {
            return false;
        }
    }
}

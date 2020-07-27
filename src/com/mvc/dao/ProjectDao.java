package com.mvc.dao;

import com.mvc.crud.Crud;

import java.sql.ResultSet;

public class ProjectDao {

    public ResultSet allProject(int userId, String state) throws Exception{

        ResultSet rst = null;
        if (state.equals("EMPLOYEE")){
            rst = Crud.executeQuery("select project.project_id, project.project_name from project, employee_work, employee, user_login where project.project_id=employee_work.project_id && employee_work.employee_id=employee.employee_id &&employee.user_id=user_login.user_id && user_login.user_id=?",userId);
        }else if (state.equals("ADMIN")){
            rst = Crud.executeQuery("select * from project");
        }

        return rst;

    }

    public int addProject(String projectName)throws Exception{

        int i = Crud.executeUpdate("INSERT INTO project (project_name) VALUES (?)",projectName);

        if (i>0){
            int projectId=0;
            ResultSet rst = Crud.executeQuery("select project_id from project where project_id=(select max(project_id) from project)");
            while (rst.next()){
                projectId = rst.getInt(1);
            }
            return projectId;
        }else {
            return i;
        }


    }
}

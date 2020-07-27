package com.mvc.dao;

import com.mvc.bean.FilterDataBean;
import com.mvc.bean.TaskBean;
import com.mvc.crud.Crud;

import java.sql.ResultSet;

public class TaskDao {

    public boolean addTask(TaskBean taskBean) throws Exception {

        int i = Crud.executeUpdate("INSERT INTO task (feature_id, employee_id, task_details, date," +
                " task_type, number_of_hours, comments) VALUES (?,?,?,?,?,?,?)",
                taskBean.getMainFeature(),
                taskBean.getEmployeeId(),
                taskBean.getTaskDetail(),
                taskBean.getDate(),
                taskBean.getTaskType(),
                taskBean.getNumberOfHours(),
                taskBean.getComments()
        );

        if (i>0){
            return true;
        }
        return false;
    };

    public ResultSet getAllTask (FilterDataBean dataBean) throws Exception {
//        ResultSet rst = Crud.executeQuery("select task.task_id, employee.user_id, project.project_id, user_login.first_name, user_login.last_name, project.project_name, employee.designation , feature.feature_name, task.task_details , task.date , task.task_type , task.number_of_hours , task.comments from feature,task,employee,project,user_login where user_login.user_id=employee.user_id && feature.project_id=project.project_id && feature.feature_id=task.feature_id && task.employee_id=employee.employee_id order by task.task_id DESC");

        ResultSet rst = Crud.executeQuery("select task.task_id, employee.user_id, project.project_id, user_login.first_name, user_login.last_name, project.project_name, employee.designation , feature.feature_name, task.task_details , task.date , task.task_type , task.number_of_hours , task.comments from feature,task,employee,project,user_login where user_login.user_id=employee.user_id && feature.project_id=project.project_id && feature.feature_id=task.feature_id && task.employee_id=employee.employee_id  \n" +
                "&& (? IS NULL OR employee.user_id = ?)\n" +
                "&& (? IS NULL OR project.project_id = ?)\n" +
                "&& (? IS NULL OR date(task.date) between date(?) and date(?))\n" +
                " order by task.task_id DESC;",
                dataBean.getUserId(),
                dataBean.getUserId(),
                dataBean.getProjectId(),
                dataBean.getProjectId(),
                dataBean.getStartDate(),
                dataBean.getStartDate(),
                dataBean.getEndDate());

        return rst;
    };
}

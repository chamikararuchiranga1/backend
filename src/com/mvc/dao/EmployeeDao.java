package com.mvc.dao;

import com.mvc.crud.Crud;
import java.sql.ResultSet;

public class EmployeeDao {

    public ResultSet allEmployee() throws Exception{
        ResultSet rst = Crud.executeQuery("select employee.employee_id, user_login.user_id, user_login.first_name, user_login.last_name, employee.designation from employee, user_login where employee.user_id=user_login.user_id group by user_login.user_id");

        return rst;

    }

    public boolean update (String id, String state)throws Exception{

        int i = Crud.executeUpdate("update employee set status=? where employee_id=?",state,id);

        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    public int add (String id, String designation)throws Exception{
        int i = Crud.executeUpdate("INSERT INTO employee (user_id, designation, status) VALUES (?,?,?)",id ,designation, "ACTIVE");

        if (i>0){
            int employeeId=0;
            ResultSet rst = Crud.executeQuery("select employee_id from employee where employee_id=(select max(employee_id) from employee)");
            while (rst.next()){
                employeeId = rst.getInt(1);
            }
            return employeeId;
        }else {
            return i;
        }
    }

}

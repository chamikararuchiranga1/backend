package com.mvc.dao;

import com.mvc.bean.FeatureBean;
import com.mvc.crud.Crud;

import java.sql.ResultSet;
import java.util.List;

public class FeatureDao {

    public boolean addFeature(List<FeatureBean> features, int projectId)throws Exception{

        int i= 0;
        for (int z=0; z<features.size(); z++){
            i = Crud.executeUpdate("INSERT INTO feature(project_id, feature_name) VALUES (?,?)",projectId, features.get(z).getFeature());
        }

        if (i>0){
            return true;
        }else {
            return false;
        }

    }

    public ResultSet allFeatures (String projectId)throws Exception{
        ResultSet rst = Crud.executeQuery("select feature_id, feature_name from feature where project_id=?", projectId);

        return rst;
    }

    public boolean delete(String featureId)throws Exception{
        int i = Crud.executeUpdate("delete from feature where feature_id=?",featureId);

        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean update (String id, String feature)throws Exception{

        int i = Crud.executeUpdate("INSERT INTO feature (project_id, feature_name) VALUES (?,?)",id,feature);

        if (i>0){
            return true;
        }else {
            return false;
        }
    }
}

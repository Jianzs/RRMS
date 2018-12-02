package com.zwl.rrms.dao;

import com.zwl.rrms.entity.CountyEntity;
import com.zwl.rrms.entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountyDao extends BaseDao {
    private static String getById =
            "SELECT * " +
            "FROM county " +
            "WHERE id = ?";

    public static CountyEntity getById(Integer id)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (CountyEntity) getById(CountyEntity.class, "county", id);
    }
}

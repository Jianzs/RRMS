package com.zwl.rrms.dao;

import com.zwl.rrms.entity.ProvinceEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDao extends BaseDao{
    private static String listAll =
            "SELECT * " +
            "FROM province";

    public static ProvinceEntity getById(Integer id)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (ProvinceEntity) getById(ProvinceEntity.class, "province", id);
    }

    public static List<ProvinceEntity> listAll()
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listAll);
        ResultSet rs = stmt.executeQuery();

        List<ProvinceEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ProvinceEntity) getObject(ProvinceEntity.class, rs));
        }
        return entities;
    }
}

package com.zwl.rrms.dao;

import com.zwl.rrms.entity.CityEntity;
import com.zwl.rrms.entity.CountyEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao extends BaseDao {
    private static String listByPid =
            "SELECT * " +
            "FROM city " +
            "WHERE province_id = ?";
    public static CityEntity getById(Integer id)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (CityEntity) getById(CityEntity.class, "city", id);
    }

    public static List<CityEntity> listByPid(Integer pid)
            throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listByPid);
        stmt.setInt(1, pid);
        ResultSet rs = stmt.executeQuery();
        List<CityEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((CityEntity) getObject(CityEntity.class, rs));
        }
        return entities;
    }
}

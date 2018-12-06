package com.zwl.rrms.dao;

import com.zwl.rrms.entity.CountyEntity;
import com.zwl.rrms.entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountyDao extends BaseDao {
    private static String listByCid =
            "SELECT * " +
            "FROM county " +
            "WHERE city_id = ?";
    public static CountyEntity getById(Integer id)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (CountyEntity) getById(CountyEntity.class, "county", id);
    }

    public static List<CountyEntity> listByCid(Integer cid) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listByCid);
        stmt.setInt(1, cid);
        ResultSet rs = stmt.executeQuery();
        List<CountyEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((CountyEntity) getObject(CountyEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }
}

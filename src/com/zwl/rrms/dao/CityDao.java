package com.zwl.rrms.dao;

import com.zwl.rrms.entity.CityEntity;
import com.zwl.rrms.entity.CountyEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDao extends BaseDao {
    public static CityEntity getById(Integer id)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (CityEntity) getById(CityEntity.class, "city", id);
    }
}

package com.zwl.rrms.dao;

import com.zwl.rrms.entity.ProvinceEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProvinceDao extends BaseDao{
    public static ProvinceEntity getById(Integer id)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (ProvinceEntity) getById(ProvinceEntity.class, "province", id);
    }

}

package com.zwl.rrms.controller;

import com.zwl.rrms.dao.ProvinceDao;
import com.zwl.rrms.entity.ProvinceEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProvinceController extends BaseController {
    public static ProvinceEntity getById(Integer id) {
        try {
            return ProvinceDao.getById(id);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | SQLException | NoSuchMethodException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return null;
    }
}

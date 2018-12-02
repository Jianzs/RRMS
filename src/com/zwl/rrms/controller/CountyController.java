package com.zwl.rrms.controller;

import com.zwl.rrms.dao.CountyDao;
import com.zwl.rrms.entity.CountyEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class CountyController extends BaseController {
    public static CountyEntity getById(Integer id) {
        try {
            return CountyDao.getById(id);
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            exceptionHand(e);
        }

        return null;
    }
}

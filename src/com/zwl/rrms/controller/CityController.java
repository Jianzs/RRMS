package com.zwl.rrms.controller;

import com.zwl.rrms.dao.CityDao;
import com.zwl.rrms.entity.CityEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class CityController extends BaseController {
    public static CityEntity getById(Integer id) {
        try {
            return CityDao.getById(id);
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            exceptionHand(e);
        }

        return null;
    }
}

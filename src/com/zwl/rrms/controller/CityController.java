package com.zwl.rrms.controller;

import com.zwl.rrms.dao.CityDao;
import com.zwl.rrms.entity.CityEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CityController extends BaseController {
    public static CityEntity getById(Integer id) {
        try {
            return CityDao.getById(id);
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            exceptionHand(e);
        }

        return null;
    }

    public static List<CityEntity> listByPid(Integer pid) {
        try {
            return CityDao.listByPid(pid);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }
}

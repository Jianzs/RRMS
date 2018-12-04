package com.zwl.rrms.controller;

import com.zwl.rrms.dao.CountyDao;
import com.zwl.rrms.entity.CountyEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CountyController extends BaseController {
    public static CountyEntity getById(Integer id) {
        try {
            return CountyDao.getById(id);
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            exceptionHand(e);
        }

        return null;
    }

    public static List<CountyEntity> listByCid(Integer cid) {
        try {
            return CountyDao.listByCid(cid);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return null;
    }
}

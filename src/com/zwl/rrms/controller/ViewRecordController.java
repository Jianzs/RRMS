package com.zwl.rrms.controller;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.dao.ViewRecordDao;
import com.zwl.rrms.entity.ViewRecordEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ViewRecordController extends BaseController {
    public static List<ViewRecordEntity> listRecordByPage(int page) {
        Set<Integer> states = new TreeSet<>();
        states.add(ViewRecord.State.NEW);

        List<ViewRecordEntity> entities = new ArrayList<>();
        try {
            entities =  ViewRecordDao.listByPageAndState(page, states, Session.getInstance().getUser());
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            exceptionHand(e);
        }

        return entities;
    }

    public static List<ViewRecordEntity> listAll() {
        return listAllByPage(1);
    }

    public static List<ViewRecordEntity> listAllByPage(Integer page) {
        try {
            return ViewRecordDao.listAllByPage(page);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static Integer countAll() {
        try {
            return ViewRecordDao.countAll();
        } catch (SQLException | ClassNotFoundException e) {
           exceptionHand(e);
        }
        return 1;
    }
}

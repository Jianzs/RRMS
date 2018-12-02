package com.zwl.rrms.controller;

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
            entities =  ViewRecordDao.listByPageAndState(page, states);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            exceptionHand(e);
        }

        return entities;
    }
}

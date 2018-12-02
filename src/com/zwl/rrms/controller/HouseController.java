package com.zwl.rrms.controller;

import com.zwl.rrms.constant.House;
import com.zwl.rrms.dao.HouseDao;
import com.zwl.rrms.dao.UserDao;
import com.zwl.rrms.entity.HouseEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public class HouseController extends BaseController {
    public static HouseEntity getHouseById(Integer houseId) {
        try {
            return HouseDao.getById(houseId);
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static List<HouseEntity> listHouseByPage(int page) {
        Integer state = House.State.NOT_RENT;
        try {
            return HouseDao.listByPageAndState(page, state);
        } catch (SQLException | InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static List<HouseEntity> listHouseByUserAndPage(Integer uid, int page) {
        Set<Integer> states = new HashSet<>();
        states.add(House.State.NOT_RENT);
        states.add(House.State.UNPAID);
        states.add(House.State.RENTED);

        List<HouseEntity> entities = new ArrayList<>();
        try {
            entities = HouseDao.listByUserAndPageAndState(uid, page, states);
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            exceptionHand(e);
        }
        return entities;
    }
}

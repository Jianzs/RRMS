package com.zwl.rrms.controller;

import com.zwl.rrms.constant.House;
import com.zwl.rrms.dao.HouseDao;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;

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

    public static Integer countByUser(UserEntity user) {
        try {
            return HouseDao.countAllByUid(user.getId());
        } catch (SQLException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static List<HouseEntity> listAllByPage(Integer page) {
        try {
            return HouseDao.listAllByPage(page);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static List<HouseEntity> listFuzzyByNeighborhood(String text) {
        try {
            return HouseDao.listFuzzyByNeighborhood(text);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
           exceptionHand(e);
        }
        return null;
    }

    public static List<HouseEntity> listFuzzyByRoomerPhone(String text)  {
        try {
            return HouseDao.listFuzzyByRoomerPhone(text);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer countAll() {
        try {
            return HouseDao.countAll();
        } catch (SQLException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return 1;
    }

    public static boolean create(HouseEntity house) {
        try {
            return HouseDao.create(house);
        } catch (SQLException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return false;
    }

    public static boolean update(HouseEntity makeHouse) {
        try {
            return HouseDao.update(makeHouse);
        } catch (SQLException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return false;
    }

    public static boolean delete(HouseEntity contact) {
        try {
            return HouseDao.deleteById(contact.getId());
        } catch (SQLException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return false;
    }

    public static int countActiveAll() {
        try {
            return HouseDao.countByState(House.State.NOT_RENT);
        } catch (SQLException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return 1;
    }
}

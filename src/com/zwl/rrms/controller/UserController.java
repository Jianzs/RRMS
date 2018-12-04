package com.zwl.rrms.controller;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.User;
import com.zwl.rrms.dao.UserDao;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.util.MD5Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UserController extends BaseController{
    public static boolean login(String phone, String password) {
        UserEntity user = null;
        try {
            user = UserDao.getByPhone(phone);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        if (user == null) return false;

        if (user.getPassword().equals(MD5Util.crypt(password))) {
            Session.getInstance().setUser(user);
            return true;
        } else {
            return false;
        }
    }

    public static UserEntity getUserById(Integer roomerId) {
        try {
            return UserDao.getById(roomerId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean register(UserEntity user) {
        boolean succ = false;
        try {
            succ = UserDao.save(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return succ;
    }

    public static boolean update(UserEntity user) {
        Session.getInstance().setUser(user);
        boolean succ = false;
        try {
            succ = UserDao.update(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return succ;
    }

    public static List<UserEntity> listAllByPage(Integer page) {
        try {
            return UserDao.listByStateAndPage(User.State.NORMAL, page);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static boolean loginAdmin(String phone, String password) {
        UserEntity user = null;
        try {
            user = UserDao.getByPhone(phone);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        if (user == null) return false;
        if (!user.getType().equals(User.Type.ADMIN)) return false;


        if (user.getPassword().equals(MD5Util.crypt(password))) {
            Session.getInstance().setUser(user);
            return true;
        } else {
            return false;
        }
    }

    public static Integer countAll() {
        try {
            return UserDao.countAll();
        } catch (SQLException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return 1;
    }

    public static List<UserEntity> listByPhone(String text) {
        try {
            return UserDao.listByPhone(text);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static List<UserEntity> listFuzzyByName(String text) {
        try {
            return UserDao.listFuzzyByName(text);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return null;
    }
}

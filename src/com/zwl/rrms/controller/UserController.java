package com.zwl.rrms.controller;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.dao.UserDao;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.util.MD5Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserController {
    public static boolean login(String phone, String password) {
        UserEntity user = null;
        try {
            user = UserDao.getByPhone(phone);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
}

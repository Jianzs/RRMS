package com.zwl.rrms.controller;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.Contact;
import com.zwl.rrms.dao.ContactDao;
import com.zwl.rrms.entity.ContactEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactController extends BaseController {

    public static List<ContactEntity> listContactByPage(int page) {
        List<ContactEntity> entities = new ArrayList<>();

        try {
            entities = ContactDao.listByPageAndState(page, Contact.State.NORMAL, Session.getInstance().getUser().getId());
        } catch (InstantiationException | InvocationTargetException | SQLException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return entities;
    }

    public static List<ContactEntity> listAllByPage(Integer page) {
        try {
            return ContactDao.listAllByPage(page);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static List<ContactEntity> listByRoomerPhone(String text) {
        try {
            return ContactDao.listByRoomerPhone(text);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static List<ContactEntity> listByRenterPhone(String text) {
        try {
            return ContactDao.listByRenterPhone(text);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static List<ContactEntity> listFuzzyByNeighborhood(String text) {
        try {
            return ContactDao.listFuzzyByNeighborhood(text);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static ContactEntity getById(int id) {
        try {
            return ContactDao.getById(id);
        } catch (InvocationTargetException | SQLException | NoSuchMethodException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return null;
    }

    public static boolean delete(ContactEntity contact) {
        try {
            return ContactDao.deleteById(contact.getId());
        } catch (SQLException | ClassNotFoundException e) {
            exceptionHand(e);
        }
        return false;
    }
}

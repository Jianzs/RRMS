package com.zwl.rrms.controller;

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
            entities = ContactDao.listByPageAndState(page, Contact.State.NORMAL);
        } catch (InstantiationException | InvocationTargetException | SQLException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException e) {
            exceptionHand(e);
        }
        return entities;
    }
}

package com.zwl.rrms.dao;

import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.entity.ContactEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao extends BaseDao {
    private static String listByPageAndState =
            "SELECT * " +
            "FROM contact " +
            "WHERE state = ? " +
            "LIMIT ?, ?";

    public static List<ContactEntity> listByPageAndState(int page, int state)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listByPageAndState);
        stmt.setInt(1, state);
        stmt.setInt(2, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(3, Parameter.NUM_HOUSE_PER_PAGE);

        ResultSet rs = stmt.executeQuery();

        System.out.println(stmt.toString());
        List<ContactEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ContactEntity) getObject(ContactEntity.class, rs));
        }

        close(conn, rs, stmt);

        return entities;
    }
}

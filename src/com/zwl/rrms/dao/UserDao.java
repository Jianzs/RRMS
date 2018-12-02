package com.zwl.rrms.dao;

import com.zwl.rrms.constant.User;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.util.MD5Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDao {
    private static String getByPhone =
            "SELECT * " +
            "FROM user " +
            "WHERE phone = ? AND state = ?";

    private static String getById =
            "SELECT * " +
            "FROM user " +
            "WHERE id = ?";

    public static UserEntity getByPhone(String phone)
            throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(getByPhone);
        stmt.setString(1, phone);
        stmt.setInt(2, User.State.NORMAL);
        ResultSet rs = stmt.executeQuery();

        UserEntity user = null;
        if (rs.next()) {
            user = (UserEntity) getObject(UserEntity.class, rs);
        }

        close(conn, rs, stmt);
        return user;
    }

    public static UserEntity getById(Integer id)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (UserEntity) getById(UserEntity.class, "user", id);
    }
}

package com.zwl.rrms.dao;

import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.constant.User;
import com.zwl.rrms.entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao {
    private static String getByPhone =
            "SELECT * " +
            "FROM user " +
            "WHERE phone = ? AND state = ?";

    private static String listByPhone =
            "SELECT * " +
            "FROM user " +
            "WHERE phone = ?";

    private static String save =
            "insert into user(name, province_id, city_id, county_id, " +
                    "address, phone, birthday, gender, state, password, type) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, md5(?), ?)";

    private static String update =
            "update user\n" +
            "set name = ?,\n" +
            "    province_id = ?,\n" +
            "    city_id = ?,\n" +
            "    county_id = ?,\n" +
            "    address = ?,\n" +
            "    password = md5(?),\n" +
            "    birthday = ?,\n" +
            "    gender = ?,\n" +
            "    state = ?\n" +
            "where id = ?";

    private static String listByState =
            "SELECT * " +
            "FROM user " +
            "WHERE state = ? " +
            "LIMIT ?, ?";
    private static String countAll =
            "select count(*)\n" +
            "from user\n" +
            "where state <> ?\n";
    private static String listFuzzyByName =
            "select *\n" +
            "from user\n" +
            "where name like '%%%s%%'";

    public static boolean deleteById(Integer id) throws SQLException, ClassNotFoundException {
        return deleteById("user", User.State.DELETED, id);
    }

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

    public static boolean save(UserEntity user)
            throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(save);
        stmt.setString(1, user.getName());
        stmt.setInt(2, user.getProvinceId());
        stmt.setInt(3, user.getCityId());
        stmt.setInt(4, user.getCountyId());
        stmt.setString(5, user.getAddress());
        stmt.setString(6, user.getPhone());
        stmt.setLong(7, user.getBirthday());
        stmt.setInt(8, user.getGender());
        stmt.setInt(9, user.getState());
        stmt.setString(10, user.getPassword());
        stmt.setInt(11, user.getType());

        int res = stmt.executeUpdate();
        close(conn, null, stmt);
        return res == 1;
    }

    public static boolean update(UserEntity user)
            throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(update);
        stmt.setString(1, user.getName());
        stmt.setInt(2, user.getProvinceId());
        stmt.setInt(3, user.getCityId());
        stmt.setInt(4, user.getCountyId());
        stmt.setString(5, user.getAddress());
        stmt.setString(6, user.getPhone());
        stmt.setLong(7, user.getBirthday());
        stmt.setInt(8, user.getGender());
        stmt.setInt(9, user.getState());
        stmt.setInt(10, user.getId());
        int res = stmt.executeUpdate();
        close(conn, null, stmt);
        return res == 1;
    }

    public static List<UserEntity> listByStateAndPage(int state, Integer page)
            throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listByState);
        stmt.setInt(1, state);
        stmt.setInt(2, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(3, Parameter.NUM_HOUSE_PER_PAGE);
        ResultSet rs = stmt.executeQuery();
        List<UserEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((UserEntity) getObject(UserEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }

    public static Integer countAll() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(countAll);
        stmt.setInt(1, User.State.DELETED);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int ans = rs.getInt(1);
        close(conn, rs, stmt);
        return ans;
    }

    public static List<UserEntity> listByPhone(String text) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listByPhone);
        stmt.setString(1, text);
        ResultSet rs = stmt.executeQuery();
        List<UserEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((UserEntity) getObject(UserEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }

    public static List<UserEntity> listFuzzyByName(String text) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format(listFuzzyByName, text));
        ResultSet rs = stmt.executeQuery();
        List<UserEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((UserEntity) getObject(UserEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }
}

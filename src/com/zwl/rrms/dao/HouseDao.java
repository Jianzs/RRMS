package com.zwl.rrms.dao;

import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class HouseDao extends BaseDao {
    private static String listByPageAndState =
            "SELECT * " +
            "FROM house " +
            "WHERE state = ? " +
            "LIMIT ?, ?";

    private static String listByUserAndPageAndStates =
            "SELECT * " +
            "FROM house " +
            "WHERE roomer_id = ? " +
            "   AND state in (%s) " +
            "LIMIT ?, ?";

    public static HouseEntity getById(Integer houseId)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (HouseEntity) getById(HouseEntity.class, "house", houseId);
    }

    public static List<HouseEntity> listByPageAndState(Integer page, Integer state)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Connection conn = getConnection();

        PreparedStatement stmt = conn.prepareStatement(listByPageAndState);
        stmt.setInt(1, state);
        stmt.setInt(2, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(3, Parameter.NUM_HOUSE_PER_PAGE);
        ResultSet rs = stmt.executeQuery();

        List<HouseEntity> houses = new ArrayList<>();
        while (rs.next()) {
            HouseEntity house = (HouseEntity) getObject(HouseEntity.class, rs);
            houses.add(house);
        }

        close(conn, rs, stmt);
        return houses;
    }


    public static List<HouseEntity> listByUserAndPageAndState(Integer uid, int page, Set<Integer> states)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int i, len;
        StringBuilder qMark = new StringBuilder();
        for (i = 0, len = states.size(); i < len; i++) {
            if (i != 0) qMark.append(",");
            qMark.append("?");
        }

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format(listByUserAndPageAndStates, qMark.toString()));

        i = 1;
        stmt.setInt(i++, uid);
        for (Integer state: states) {
            stmt.setInt(i, state);
            i++;
        }
        stmt.setInt(i++, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(i, Parameter.NUM_HOUSE_PER_PAGE);

        ResultSet rs = stmt.executeQuery();

        System.out.println(stmt.toString());
        List<HouseEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((HouseEntity) getObject(HouseEntity.class, rs));
        }

        close(conn, rs, stmt);

        return entities;
    }
}

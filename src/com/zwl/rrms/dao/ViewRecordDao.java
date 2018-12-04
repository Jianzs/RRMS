package com.zwl.rrms.dao;

import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

import javax.sql.rowset.serial.SerialArray;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ViewRecordDao extends BaseDao {
    private static String listByPageAndState =
            "SELECT * " +
            "FROM view_record " +
            "WHERE state in (%s) " +
                    "AND renter_id = ? " +
            "LIMIT ?, ?";

    private static String listAll =
            "SELECT * " +
            "FROM view_record " +
            "LIMIT ?, ?";

    private static String countAll =
            "SELECT COUNT(*)\n" +
            "FROM view_record";

    public static List<ViewRecordEntity> listByPageAndState(Integer page, Collection<Integer> states, UserEntity renter)
            throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        int i, len;
        StringBuilder qMark = new StringBuilder();
        for (i = 0, len = states.size(); i < len; i++) {
            if (i != 0) qMark.append(",");
            qMark.append("?");
        }

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format(listByPageAndState, qMark.toString()));

        i = 1;
        for (Integer state: states) {
            stmt.setInt(i, state);
            i++;
        }
        stmt.setInt(i++, renter.getId());
        stmt.setInt(i++, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(i, Parameter.NUM_HOUSE_PER_PAGE);

        ResultSet rs = stmt.executeQuery();

        System.out.println(stmt.toString());
        List<ViewRecordEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ViewRecordEntity) getObject(ViewRecordEntity.class, rs));
        }

        close(conn, rs, stmt);

        return entities;
    }

    public static List<ViewRecordEntity> listAllByPage(Integer page)
            throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listAll);
        stmt.setInt(1, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(2, Parameter.NUM_HOUSE_PER_PAGE);
        ResultSet rs = stmt.executeQuery();

        List<ViewRecordEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ViewRecordEntity) getObject(ViewRecordEntity.class, rs));
        }
        return entities;
    }

    public static Integer countAll() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(countAll);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}

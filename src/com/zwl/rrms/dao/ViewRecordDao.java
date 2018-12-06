package com.zwl.rrms.dao;

import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

import javax.sql.rowset.serial.SerialArray;
import javax.swing.text.View;
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

    private static String create =
            "insert into view_record(plan_time, renter_id, room_id, \n" +
            "                        description, roomer_ack, admin_ack, state)\n" +
            "values (?,?,?,?,?,?,?)";

    private static String updateAdminAckById =
            "update view_record\n" +
            "set admin_ack = ?\n" +
            "where id = ?";

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
        close(conn, rs, stmt);
        return entities;
    }

    public static boolean deleteById(Integer id) throws SQLException, ClassNotFoundException {
        return deleteById("view_record", ViewRecord.State.DELETED, id);
    }

    public static Integer countAll() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(countAll);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int ans = rs.getInt(1);
        close(conn, rs, stmt);
        return ans;
    }

    public static boolean create(ViewRecordEntity view) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(create);
        stmt.setInt(1, view.getPlanTime());
        stmt.setInt(2, view.getRenterId());
        stmt.setInt(3, view.getRoomId());
        stmt.setString(4, view.getDescription());
        stmt.setInt(5, view.getRoomerAck());
        stmt.setInt(6, view.getAdminAck());
        stmt.setInt(7, view.getState());
        int i = stmt.executeUpdate();
        close(conn, null, stmt);
        return i > 0;
    }

    public static boolean updateAdminAckById(Integer state, Integer id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(updateAdminAckById);
        stmt.setInt(1, state);
        stmt.setInt(2, id);
        int i = stmt.executeUpdate();
        close(conn, null, stmt);
        return  i > 0;
    }

    public static ViewRecordEntity getById(Integer id) throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return (ViewRecordEntity) getById(ViewRecordEntity.class, "view_record", id);
    }
}

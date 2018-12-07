package com.zwl.rrms.dao;

import com.zwl.rrms.constant.Contact;
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
    private static String save =
        "insert into contact(start_time, end_time, state, \n" +
        "                    room_id, renter_id, rent, lease_term, remark) \n" +
        "values (?,?,?,?,?,?,?,?)";

    private static String listByPageAndState =
            "SELECT * " +
            "FROM contact " +
            "WHERE state = ? " +
            "AND renter_id = ? " +
            "LIMIT ?, ?";

    private static String listAll =
            "SELECT * " +
            "FROM contact " +
            "WHERE state <> ? " +
            "LIMIT ?, ?";
    private static String countAll =
            "select count(*)\n" +
                    "from contact\n";

    private static String listByRoomerPhone =
            "select *\n" +
                    "from contact\n" +
                    "where roomer_id in (select id\n" +
                    "                 from user\n" +
                    "                 where phone = ?)";

    private static String listByRenterPhone =
            "select *\n" +
                    "from contact\n" +
                    "where renter_id in (select id\n" +
                    "                 from user\n" +
                    "                 where phone = ?)";
    private static String listFuzzyByNeighborhood =
            "select *\n" +
            "from contact\n" +
            "where room_id in (select id\n" +
            "                 from house\n" +
            "                 where neighborhood like '%%%s%%')";

    public static List<ContactEntity> listByPageAndState(int page, int state, int uid)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listByPageAndState);
        stmt.setInt(1, state);
        stmt.setInt(2, uid);
        stmt.setInt(3, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(4, Parameter.NUM_HOUSE_PER_PAGE);

        ResultSet rs = stmt.executeQuery();

        System.out.println(stmt.toString());
        List<ContactEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ContactEntity) getObject(ContactEntity.class, rs));
        }

        close(conn, rs, stmt);

        return entities;
    }

    public static List<ContactEntity> listAllByPage(Integer page)
            throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listAll);
        stmt.setInt(1, Contact.State.DELETED);
        stmt.setInt(2, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(3, Parameter.NUM_HOUSE_PER_PAGE);
        ResultSet rs = stmt.executeQuery();

        List<ContactEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ContactEntity) getObject(ContactEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
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

    public static List<ContactEntity> listByRoomerPhone(String text) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listByRoomerPhone);
        stmt.setString(1, text);
        ResultSet rs = stmt.executeQuery();
        List<ContactEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ContactEntity) getObject(ContactEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }

    public static List<ContactEntity> listByRenterPhone(String text) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listByRenterPhone);
        stmt.setString(1, text);
        ResultSet rs = stmt.executeQuery();
        List<ContactEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ContactEntity) getObject(ContactEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }

    public static List<ContactEntity> listFuzzyByNeighborhood(String text) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format(listFuzzyByNeighborhood, text));
        ResultSet rs = stmt.executeQuery();
        List<ContactEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((ContactEntity) getObject(ContactEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }

    public static ContactEntity getById(int id) throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return (ContactEntity) getById(ContactEntity.class, "contact", id);
    }

    public static boolean deleteById(Integer id) throws SQLException, ClassNotFoundException {
        return deleteById("contact", Contact.State.DELETED, id);
    }

    public static boolean create(ContactEntity contact)
            throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(save);
        stmt.setLong(1, contact.getStartTime());
        stmt.setLong(2, contact.getEndTime());
        stmt.setInt(3, contact.getState());
        stmt.setInt(4, contact.getRoomId());
        stmt.setInt(5, contact.getRenterId());
        stmt.setDouble(6, contact.getRent());
        stmt.setString(7, contact.getLeaseTerm());
        stmt.setString(8, contact.getRemark());
        int rs = stmt.executeUpdate();
        close(conn, null, stmt);
        return rs > 0;
    }
}

package com.zwl.rrms.dao;

import com.zwl.rrms.constant.House;
import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.constant.User;
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

    private static String countAllByUid =
            "select count(*)\n" +
            "from house\n" +
            "where roomer_id = ?";

    private static String listAll =
            "SELECT * " +
            "FROM house " +
            "LIMIT ?, ?";

    private static String listFuzzyByNeighborhood =
            "select *\n" +
            "from house\n" +
            "where neighborhood like '%%%s%%'";

    private static String listFuzzyByRoomerPhone =
            "select *\n" +
            "from house\n" +
            "where roomer_id = (select id\n" +
            "                  from user\n" +
            "                  where phone = ?\n" +
            "                    and state = ?)";
    private static String countAll =
            "select count(*)\n" +
            "from house";

    private static String create =
            "insert into house(neighborhood, province_id, city_id, county_id, address, type, max_customer, rent, state, service_charge, roomer_id, description, freetime, picture)\n" +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static String update =
            "update house\n" +
                    "set neighborhood = ?,\n" +
                    "    province_id = ?,\n" +
                    "    city_id = ?,\n" +
                    "    county_id = ?,\n" +
                    "    address = ?,\n" +
                    "    type = ?,\n" +
                    "    max_customer = ?,\n" +
                    "    rent = ?,\n" +
                    "    state = ?,\n" +
                    "    service_charge = ?,\n" +
                    "    roomer_id = ?,\n" +
                    "    description = ?,\n" +
                    "    freetime = ?,\n" +
                    "    picture = ?\n" +
                    "where id = ?";

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

    public static Integer countAllByUid(Integer uid)
            throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(countAllByUid);
        stmt.setInt(1, uid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int ans = rs.getInt(1);
        close(conn, rs, stmt);
        return ans;
    }

    public static List<HouseEntity> listAllByPage(Integer page)
            throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listAll);
        stmt.setInt(1, Parameter.NUM_HOUSE_PER_PAGE * (page - 1));
        stmt.setInt(2, Parameter.NUM_HOUSE_PER_PAGE);
        ResultSet rs = stmt.executeQuery();

        List<HouseEntity> entities = new ArrayList<>();
        while (rs.next()){
            entities.add((HouseEntity) getObject(HouseEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }

    public static List<HouseEntity> listFuzzyByNeighborhood(String text)
            throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format(listFuzzyByNeighborhood, text));
        ResultSet rs = stmt.executeQuery();
        List<HouseEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((HouseEntity) getObject(HouseEntity.class, rs));
        }
        close(conn, rs, stmt);
        return entities;
    }

    public static List<HouseEntity> listFuzzyByRoomerPhone(String text) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(listFuzzyByRoomerPhone);
        stmt.setString(1, text);
        stmt.setInt(2, User.State.NORMAL);
        ResultSet rs = stmt.executeQuery();
        List<HouseEntity> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add((HouseEntity) getObject(HouseEntity.class, rs));
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

    public static boolean create(HouseEntity house) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(create);
        stmt.setString(1, house.getNeighborhood());
        stmt.setInt(2, house.getProvinceId());
        stmt.setInt(3, house.getCityId());
        stmt.setInt(4, house.getCountyId());
        stmt.setString(5, house.getAddress());
        stmt.setInt(6, house.getType());
        stmt.setInt(7, house.getMaxCustomer());
        stmt.setDouble(8, house.getRent());
        stmt.setInt(9, house.getState());
        stmt.setDouble(10, house.getServiceCharge());
        stmt.setInt(11, house.getRoomerId());
        stmt.setString(12, house.getDescription());
        stmt.setInt(13, house.getFreetime());
        stmt.setString(14, house.getPicture());

        System.out.println(house.getServiceCharge());
        int i = stmt.executeUpdate();
        close(conn, null, stmt);
        return i > 0;
    }

    public static boolean deleteById(Integer id) throws SQLException, ClassNotFoundException {
        return deleteById("house", House.State.DELETED, id);
    }

    public static boolean update(HouseEntity house) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(update);
        stmt.setString(1, house.getNeighborhood());
        stmt.setInt(2, house.getProvinceId());
        stmt.setInt(3, house.getCityId());
        stmt.setInt(4, house.getCountyId());
        stmt.setString(5, house.getAddress());
        stmt.setInt(6, house.getType());
        stmt.setInt(7, house.getMaxCustomer());
        stmt.setDouble(8, house.getRent());
        stmt.setInt(9, house.getState());
        stmt.setDouble(10, house.getServiceCharge());
        stmt.setInt(11, house.getRoomerId());
        stmt.setString(12, house.getDescription());
        stmt.setInt(13, house.getFreetime());
        stmt.setString(14, house.getPicture());
        stmt.setInt(15, house.getId());

        System.out.println(house.getServiceCharge());
        int i = stmt.executeUpdate();
        close(conn, null, stmt);
        return i > 0;
    }
}

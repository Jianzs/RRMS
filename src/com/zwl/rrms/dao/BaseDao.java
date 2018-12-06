package com.zwl.rrms.dao;

import com.zwl.rrms.constant.DB;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

public abstract class BaseDao {
    private static String getById =
            "SELECT * " +
            "FROM %s " +
            "WHERE id = ?";

    private static String deleteById =
            "UPDATE %s " +
            "SET state = ? " +
            "WHERE id = ? ";

    protected static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 打开链接
        return ConnPool.getConnection();
    }

    protected static void close(Connection conn, ResultSet rs, Statement stmt) throws SQLException {
        if (rs != null)
            rs.close();
        if (stmt != null)
            stmt.close();
        ConnPool.close(conn);
    }

    // getById都类似，获取对象
    protected static Object getById(Class klass, String tableName, Integer id)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format(getById, tableName));
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Object obj = null;
        if (rs.next()) {
            obj =  getObject(klass, rs);
        }

        close(conn, rs, stmt);

        return obj;
    }

    // deleteById 删除对象
    protected static boolean deleteById(String tableName, Integer state, Integer id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format(deleteById, tableName));
        stmt.setInt(1, state);
        stmt.setInt(2, id);
        int i = stmt.executeUpdate();
        close(conn, null, stmt);
        return i > 0;
    }

    // 通过反射生成对象
    protected static Object getObject(Class klass, ResultSet rs)
            throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object o = klass.newInstance();
        ResultSetMetaData metaData = rs.getMetaData();

        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String methodStr;
            Method method;

            switch (metaData.getColumnType(i)) {
                case Types.INTEGER:
                case Types.TINYINT:
                    methodStr = formatString(metaData.getColumnLabel(i));
                    method = klass.getMethod(methodStr, Integer.class);
                    method.invoke(o, rs.getInt(i));
                    break;
                case Types.BIGINT:
                    methodStr = formatString(metaData.getColumnLabel(i));
                    method = klass.getMethod(methodStr, Long.class);
                    method.invoke(o, rs.getLong(i));
                    break;
                case Types.VARCHAR:
                case Types.CHAR:
                case Types.LONGVARCHAR:
                    methodStr = formatString(metaData.getColumnLabel(i));
                    method = klass.getMethod(methodStr, String.class);
                    method.invoke(o, rs.getString(i));
                    break;
                case Types.DOUBLE:
                    methodStr = formatString(metaData.getColumnLabel(i));
                    method = klass.getMethod(methodStr, Double.class);
                    method.invoke(o, rs.getDouble(i));
                    break;
                default:
                    System.err.println("ERROR".concat(metaData.getColumnLabel(i)));
            }
        }
        return o;
    }

    private static String formatString(String origin) {
        char[] label = origin.toCharArray();
        label[0] = Character.toUpperCase(label[0]);
        for (int j = 0; j < label.length; j++) {
            if (label[j] == '_') {
                label[j+1] = Character.toUpperCase(label[j+1]);
            }
        }
        String str = String.valueOf("set" + String.valueOf(label));
        str = str.replaceAll("_", "");
        return str;
    }
}

package com.zwl.rrms.dao;

import com.zwl.rrms.constant.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

// 数据库连接池
public class ConnPool {
    private static int VALID_TIMEOUT = 3;
    private static Long INTERVAL = 20000L;
    private static Set<Connection> allConn = new HashSet<>();
    private static Set<Connection> activeConn = new HashSet<>();
    private static Queue<Connection> pendingConn = new LinkedList<>();

    private static long startTime;

    static {
        try {
            Class.forName(DB.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB.DB_URL,DB.USERNAME,DB.PASSWORD);
            pendingConn.add(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            while (true) {
                synchronized (pendingConn) {
                    try {
                        for (Connection conn: pendingConn) {
                            if (!conn.isValid(VALID_TIMEOUT)) {
                                conn.close();
                                conn = DriverManager.getConnection(DB.DB_URL,DB.USERNAME,DB.PASSWORD);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static Connection getConnection()
            throws SQLException {
        startTime = System.currentTimeMillis();

        Connection conn = null;
        synchronized (pendingConn) {
            while (!pendingConn.isEmpty()) {
                conn = pendingConn.poll();
                if (conn.isValid(VALID_TIMEOUT)) {
                    break;
                } else {
                    allConn.remove(conn);
                }
            }
        }
        if (conn == null) {
            conn = DriverManager.getConnection(DB.DB_URL,DB.USERNAME,DB.PASSWORD);
            allConn.add(conn);
        }
        activeConn.add(conn);
        return conn;
    }

    public static void close(Connection conn) {
        activeConn.remove(conn);
        synchronized (pendingConn) {
            pendingConn.add(conn);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}

package com.software.statisticssystem;

import java.sql.*;

public class SqlDB {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/grade_study";
    static String username = "root";
    static String password = "wac62461818";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    public Connection getConnection() {
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet query(String sql) {
        conn = getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

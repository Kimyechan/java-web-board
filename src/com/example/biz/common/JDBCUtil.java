package com.example.biz.common;

import java.sql.*;

public class JDBCUtil {
    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");

            return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // SELECT 기능의 자원해제
    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null ){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }

        try {
            if (stmt != null ){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {
            if (!conn.isClosed() && conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    // SELECT 기능이 아닌 것의 자원해제
    public static void close(PreparedStatement stmt, Connection conn) {

        try {
            if (stmt != null ){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {
            if (!conn.isClosed() && conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}

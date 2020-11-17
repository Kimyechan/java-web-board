package com.example.biz.user;

import com.example.biz.common.JDBCUtil;

import java.sql.*;

public class GetUserTest {
    public static void main(String[] args) {
//        // JDBC API 선언
//        Connection conn = null; // 고속도로
//        Statement stmt = null;  // 자동차
//        ResultSet rs = null;    // 검색 결과 저장
//        try {
//            // 1. 드라이버 객체를 메모리에 로딩한다.
//            Class.forName("org.h2.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            // 2. Connection
//            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
//
//            // 3. Statement 객체를 획득한다.
//            stmt = conn.createStatement();
//
//            // 4. SQL 구문을 DB에 전송한다
//            String sql = "select * from users where id = 'admin' and password = 'admin'";
//            rs = stmt.executeQuery(sql);
//
//            // 5. 검색 결과 처리
//            if(rs.next()) {
//                System.out.println("아이디 : " + rs.getString("ID"));
//                System.out.println("비번 : " + rs.getString("PASSWORD"));
//                System.out.println("이름 : " + rs.getString("NAME"));
//                System.out.println("권한 : " + rs.getString("ROLE"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // 6. Connection 연결해제
//            try {
//                if (stmt != null ){
//                    stmt.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                stmt = null;
//            }
//
//            try {
//                if (!conn.isClosed() && conn != null){
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                conn = null;
//            }
//        }
        // JDBC API 선언
        Connection conn = null; // 고속도로
        PreparedStatement stmt = null; // 자동차 (페라리)
        ResultSet rs = null;    // 검색 결과 저장


        try {
            // 2. Connection
            conn = JDBCUtil.getConnection();

            // 3. Statement 객체를 획득한다.
            String sql = "select * from users where id = ? and password = ?";
            stmt = conn.prepareStatement(sql);

            // ?(파라미터)에 값 설정
            stmt.setString(1, "admin");
            stmt.setString(2, "admin");

            // 4. SQL 구문을 DB에 전송한다
            rs = stmt.executeQuery();

            // 5. 검색 결과 처리
            if(rs.next()) {
                System.out.println("아이디 : " + rs.getString("ID"));
                System.out.println("비번 : " + rs.getString("PASSWORD"));
                System.out.println("이름 : " + rs.getString("NAME"));
                System.out.println("권한 : " + rs.getString("ROLE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
}

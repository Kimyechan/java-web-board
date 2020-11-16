package com.example.biz.board;

import java.sql.*;

/**
 * connection - 고속도로
 * statement - 자동차
 * stmt.executeUpdate(sql) - 사람을 태워서 출발
 */
public class GetBoardListTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. 드라이버 객체를 메모리에 로딩한다.
//            DriverManager.registerDriver(new org.h2.Driver()); // 컴파일 단계에서 확인
            Class.forName("org.h2.Driver"); // 클래스 path에서 찾아서 런타임에서 확인가능 메모리상에 driver가 올라감

            // 2. 커넥션 획득한다
            String url = "jdbc:h2:tcp://localhost/~/test";
            conn = DriverManager.getConnection(url, "sa", "");

            // 3. SQL 전달 객체 (Statement)를 생성한다
            stmt = conn.createStatement();

            // 4. SQL을 전송한다.
            String sql = "select * from board order by seq desc";
            rs = stmt.executeQuery(sql);

            // 5. 검색 결과 처리 (SELECT 구문 에 한하여...)
            System.out.println("[ BOARD LIST ]");

            while(rs.next()) { // B.F - DATA - A.L (처음에 B.F(null) -> next로 다음 cursor로 이동)
                System.out.print(rs.getInt("SEQ") + " : ");
                System.out.print(rs.getString("TITLE") + " : ");
                System.out.print(rs.getString("WRITER") + " : ");
                System.out.print(rs.getString("CONTENT") + " : ");
                System.out.print(rs.getDate("REGDATE") + " : ");
                System.out.print(rs.getInt("CNT"));
//                System.out.print(rs.getInt(1) + " : ");
//                System.out.print(rs.getString(2) + " : ");
//                System.out.print(rs.getString(3) + " : ");
//                System.out.print(rs.getString(4) + " : ");
//                System.out.print(rs.getDate(5) + " : ");
//                System.out.print(rs.getInt(6)); // 유지보수에 안 좋다 각 값이 뭔지 모르게때문에
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close 순서 ResultSet -> Statement -> Connection
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
    }
}

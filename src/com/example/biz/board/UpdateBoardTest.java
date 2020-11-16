package com.example.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * connection - 고속도로
 * statement - 자동차
 * stmt.executeUpdate(sql) - 사람을 태워서 출발
 */
public class UpdateBoardTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

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
            String sql = "update board set title = '제목 수정', content = '내용 수정' where seq = 1";
            int cnt = stmt.executeUpdate(sql); // executeUpdate - insert update delete
            System.out.println(cnt + " 건의 데이터 처리 성공");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

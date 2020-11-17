package com.example.biz.board;

import java.sql.*;

/**
 * connection - 고속도로
 * statement - 자동차
 * stmt.executeUpdate(sql) - 사람을 태워서 출발
 */
public class InsertBoardTest {
    public static void main(String[] args) {
        // 1. 글 등록 기능 처리
        BoardDAO boardDAO = new BoardDAO();

        BoardVO vo = new BoardVO();
        vo.setTitle("VO 테스트");
        vo.setWriter("테스터");
        vo.setContent("VO 내용 ..........");
        boardDAO.insertBoard(vo);

        // 2. 글 목록 검색 기능 처리
        boardDAO.getBoardList();

      /*  Connection conn = null;
//        Statement stmt = null;
        PreparedStatement stmt = null;

        try {
            // 1. 드라이버 객체를 메모리에 로딩한다.
//            DriverManager.registerDriver(new org.h2.Driver()); // 컴파일 단계에서 확인
            Class.forName("org.h2.Driver"); // 클래스 path에서 찾아서 런타임에서 확인가능 메모리상에 driver가 올라감

            // 2. 커넥션 획득한다
            String url = "jdbc:h2:tcp://localhost/~/test";
            conn = DriverManager.getConnection(url, "sa", "");

            // 3. SQL 전달 객체 (Statement)를 생성한다
            String sql = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from board), ?, ?, ? )";
            stmt = conn.prepareStatement(sql);

            // 파라미터 셋팅
            stmt.setString(1, "JDBC 제목");
            stmt.setString(2, "테스터");
            stmt.setString(3, "JDBC 내용..............");

            // 4. SQL을 전송한다.
//            // max(seq) -> null, nvl(max(seq), 0) -> null이면 0으로 바꿔라
//            String sql = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from board), '테스트 제목', '테스트', '테스트 내용...' )";
//            int cnt = stmt.executeUpdate(sql);
            int cnt = stmt.executeUpdate();
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
        }*/
    }
}

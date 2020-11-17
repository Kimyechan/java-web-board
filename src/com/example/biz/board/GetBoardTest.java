package com.example.biz.board;

import java.sql.*;

/**
 * connection - 고속도로
 * statement - 자동차
 * stmt.executeUpdate(sql) - 사람을 태워서 출발
 */
public class GetBoardTest {
    public static void main(String[] args) {
        /*Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. 드라이버 객체를 메모리에 로딩한다.
            Class.forName("org.h2.Driver"); // 클래스 path에서 찾아서 런타임에서 확인가능 메모리상에 driver가 올라감

            // 2. 커넥션 획득한다
            String url = "jdbc:h2:tcp://localhost/~/test";
            conn = DriverManager.getConnection(url, "sa", "");

            // 3. SQL 전달 객체 (Statement)를 생성한다
            stmt = conn.createStatement();

            // 4. SQL을 전송한다.
            String sql = "select * from board desc where seq = 1";
            rs = stmt.executeQuery(sql);

            // 5. 검색 결과 처리 (SELECT 구문 에 한하여...)
            if (rs.next()) {
                System.out.println("게시 글 상세 정보");
                System.out.println(" 번호 : " +rs.getInt("SEQ"));
                System.out.println(" 제목 : " +rs.getString("TITLE"));
                System.out.println(" 작성자 : " +rs.getString("WRITER"));
                System.out.println(" 내용 : " +rs.getString("CONTENT"));
                System.out.println(" 등록일 : " +rs.getDate("REGDATE"));
                System.out.println(" 조회수 : " +rs.getInt("CNT"));
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
        }*/
        BoardDAO boardDAO = new BoardDAO();
        BoardVO vo = new BoardVO();
        vo.setSeq(5);
        BoardVO board = boardDAO.getBoard(vo);
        boardDAO.updateCount(board);
        System.out.println("게시글 상세정보");
        System.out.println(board);
        // 2. 글 목록 검색 기능 처리
        boardDAO.getBoard(vo);
    }
}

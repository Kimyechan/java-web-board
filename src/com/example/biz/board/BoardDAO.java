package com.example.biz.board;

import com.example.biz.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// DAO(Data Access Object) 클래스
public class BoardDAO {
    // JDBC 관련 변수
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    // Board 테이블 관련 Sql 명령어
    private static final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from board), ?, ?, ? )";
    private static final String BOARD_UPDATE = "update board set title = ?, content = ? where seq = ?";
    private static final String BOARD_UPDATE_COUNT = "update board set cnt = ? where seq = ?";
    private static final String BOARD_UPDATE_CNT = "update board set cnt = cnt + 1 where seq = ?";
    private static final String BOARD_DELETE = "delete from board where seq = ?";
    private static final String BOARD_GET = "select * from board desc where seq = ?";
    private static final String BOARD_LIST = "select * from board order by seq desc";

    // BOARD 테이블 관련 CRUD 기능의 메소드
    // 글 등록
    public void insertBoard(BoardVO vo) {
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 수정
    public void updateBoard(BoardVO vo) {
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.setInt(3, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void updateCount(BoardVO vo) {
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_UPDATE_COUNT);
            stmt.setInt(1, vo.getCnt() + 1);
            stmt.setInt(2, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 삭제
    // 유지보수 측면에서 BoardVO를 매개변수로 받는다
    public void deleteBoard(BoardVO vo) {
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo) {
        BoardVO board = null;
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, vo.getSeq());
            rs = stmt.executeQuery();

            if(rs.next()) {
                board =  BoardVO.builder()
                        .seq(rs.getInt("SEQ"))
                        .title(rs.getString("TITLE"))
                        .writer(rs.getString("WRITER"))
                        .content(rs.getString("CONTENT"))
                        .regDate(rs.getDate("REGDATE"))
                        .cnt(rs.getInt("CNT"))
                        .build();

                // 검색 결과가 존재하는 경우 조회수를 증가시킨다
                stmt= conn.prepareStatement(BOARD_UPDATE_CNT);
                stmt.setInt(1, vo.getSeq());
                stmt.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return board;
    }

    // 글 목록 검색
    public List<BoardVO> getBoardList() {
        List<BoardVO> boardList = new ArrayList<>();
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_LIST);
            rs = stmt.executeQuery();
            while(rs.next()) {
                // 검색 결과 한 Row당 하나의 BoardVO 객체에 매핑한다
                BoardVO boardVO = BoardVO.builder()
                        .seq(rs.getInt("SEQ"))
                        .title(rs.getString("TITLE"))
                        .writer(rs.getString("WRITER"))
                        .content(rs.getString("CONTENT"))
                        .regDate(rs.getDate("REGDATE"))
                        .cnt(rs.getInt("CNT"))
                        .build();

                boardList.add(boardVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return boardList;
    }
}

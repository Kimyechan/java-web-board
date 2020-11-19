package com.example.biz.user;

import com.example.biz.board.BoardVO;
import com.example.biz.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// DAO(Data Access Object) 클래스
public class UserDAO {
    // JDBC 관련 변수
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    // USERS 테이블 관련 Sql 명령어
    private static final String USER_INSERT = "insert into users values(?, ?, ?, ?)";
    private static final String USER_GET = "select * from users where id = ? and password = ?";

    // BOARD 테이블 관련 CRUD 기능의 메소드
    // 글 등록
    public void insertUSER(UserVO vo) {
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_INSERT);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            stmt.setString(3, vo.getName());
            stmt.setString(4, vo.getRole());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }



    // 글 상세 조회
    public UserVO getUSER(UserVO vo) {
        UserVO user = null;
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            rs = stmt.executeQuery();

            if(rs.next()) {
                user = UserVO.builder()
                        .id(rs.getString("ID"))
                        .password(rs.getString("PASSWORD"))
                        .name(rs.getString("NAME"))
                        .role(rs.getString("ROLE"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return user;
    }

}

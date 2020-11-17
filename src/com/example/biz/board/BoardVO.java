package com.example.biz.board;

import lombok.*;

import java.sql.Date;

// VO(Value Object) 클래스
/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode*/
@Data // 위에꺼 다 포함
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
    // 테이블의 컴럼 이름(데이터 타입까지)과 동일한 맴버변수를 private 으로 선언한다
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private int cnt;

    // private 맴버 변수에 접근하는 public Getter/Setter 메소드를 작성한다
}

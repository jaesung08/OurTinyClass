package com.otc.tinyclassroom.board.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CurrentTimestamp;

@ToString
@Getter
@Setter
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Column
    private String content;

    @Column(name = "writer_id")
    private Long writerId;

//    @Temporal(TemporalType.TIMESTAMP)
    @CurrentTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    // null이면 삭제하지 않음. notnull 이면 삭제된 상태 => 검색이나 리스트 반환 시 가져오지 않음.
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deletedAt;

    // 게시글의 타입. ex) 일반, 공지, etc..
    @NotNull
    @Column(name = "board_type")
    private BoardType boardType;

    // 게시글이 어느 반에 연결되어 있는지. ex) null =>
    @Column(name = "class_number")
    private Integer classNumber;

    @Column(name = "hit_count")
    private int hitCount;

    protected Board() {

    }

    public Board(String title, String content, Long writerId, BoardType boardType, Integer classNumber) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.boardType = boardType;
        this.classNumber = classNumber;
    }

    public static Board of(String title, String content, Long writerId, BoardType boardType, Integer classNumber) {
        return new Board(title, content, writerId, boardType, classNumber);
    }
}

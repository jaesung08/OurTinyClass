package com.otc.tinyclassroom.board.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "board_type")
    private Long boardType;

}

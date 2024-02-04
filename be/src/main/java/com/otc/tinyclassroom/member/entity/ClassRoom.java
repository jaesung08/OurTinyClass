package com.otc.tinyclassroom.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;

/**
 * 게시판 의반, 학생의 반을 구분하기 위한 엔티티.
 */
@Getter
@Entity
@Table(name = "classroom")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private int grade;
    @Column(nullable = false)
    private int number;

    @ManyToMany(mappedBy = "classRooms")
    private Set<Member> members = new HashSet<>();

    protected ClassRoom() {
    }

    /**
     * 교실 생성자.
     */
    public ClassRoom(int year, int grade, int number) {
        this.year = year;
        this.grade = grade;
        this.number = number;
    }

    public static ClassRoom of(int year, int grade, int number) {
        return new ClassRoom(year, grade, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassRoom classRoom)) {
            return false;
        }
        return id != null && id.equals(classRoom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

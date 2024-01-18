package com.otc.tinyclassroom.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

/**
 * 회원 엔티티 정의 (memberId, password, name, email, point, birthday).
 *
 * @author ParkJaeseon
 */
@Getter
@ToString
@Table(name = "member", indexes = {@Index(columnList = "memberId"), @Index(columnList = "email")})
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // db에 기록되는 번호 (auto increment)

    @Column(nullable = false, unique = true)
    private String memberId; // 유저 id
    @Column(nullable = false)
    private String password; // 비밀번호
    @Column(nullable = false)
    private String name; // 유저 이름
    @Column
    private String email; // 이메일
    @Column
    private LocalDate birthday; // 생일
    @Column
    private int point; // 포인트

    protected Member() {
    }

    /**
     * 파라미터 생성자.
     */
    private Member(String memberId, String password, String name, String email, LocalDate birthday, int point) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.point = point;
    }

    /**
     * 파라미터로 부터 멤버 엔티티 객체를 생성하는 함수.
     */
    public static Member of(String memberId, String password, String name, String email, LocalDate birthday, int point) {
        return new Member(memberId, password, name, email, birthday, point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Member member)) {
            return false;
        }
        return memberId != null && memberId.equals(member.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}


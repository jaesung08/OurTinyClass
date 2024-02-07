package com.otc.tinyclassroom.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

/**
 * 회원 엔티티 정의 (memberId, password, name, email, point, birthday).
 *
 * @author ParkJaeseon
 */

@Getter
@Table(name = "member")
@SQLDelete(sql = "UPDATE member m SET m.deleted_at = current_timestamp WHERE m.id = ?")
@SQLRestriction("deleted_at is NULL")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String memberId; // 유저 id

    @ManyToOne(optional = true)
    private ClassRoom classRoom;

    @Column(nullable = false)
    private String password; // 비밀번호
    @Setter
    @Column(nullable = false)
    private String name; // 유저 이름
    @Setter
    @Column
    private String email; // 이메일
    @Column
    private LocalDate birthday; // 생일
    @Setter
    @Column
    private int point; // 포인트
    @Setter
    @Column
    private Role role; // 신분
    @Column
    private LocalDateTime deletedAt; // 삭제 여부

    protected Member() {
    }

    /**
     * 파라미터 생성자.
     */
    private Member(String memberId, ClassRoom classRoom, String password, String name, String email, LocalDate birthday, int point) {
        this.memberId = memberId;
        this.classRoom = classRoom;
        this.password = password;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.point = point;
        this.role = Role.ROLE_ADMIN;
        this.deletedAt = null; // 기본값으로 삭제되지 않은 상태로 설정
    }

    /**
     * 파라미터로 부터 멤버 엔티티 객체를 생성하는 함수.
     */
    public static Member of(String memberId, ClassRoom classRoom, String password, String name, String email, LocalDate birthday, int point) {
        return new Member(memberId, classRoom, password, name, email, birthday, point);
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


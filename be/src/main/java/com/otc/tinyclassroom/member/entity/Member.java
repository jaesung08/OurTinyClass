package com.otc.tinyclassroom.member.entity;

import static jakarta.persistence.FetchType.LAZY;

import com.otc.tinyclassroom.community.entity.AuditingFields;
import com.otc.tinyclassroom.community.entity.Heart;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 Entity.
 */
@Getter
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String memberId; // 유저 id

    @ManyToOne(fetch = LAZY)
    private ClassRoom classRoom;

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
    @Enumerated(EnumType.STRING)
    @Setter
    @Column
    private Role role; // 권한 ( 유저, 학생, 멘토, 선생님, 관리자)
    @Column
    @Setter
    private String profileUrl;
    @OneToMany(mappedBy = "member")
    private Set<Heart> hearts = new HashSet<>();

    @OneToMany(mappedBy = "member")
    private final List<MemberClassRoom> memberClassRooms = new ArrayList<>();

    protected Member() {
    }

    /**
     * 파라미터 생성자.
     */
    private Member(String memberId, String password, String name, String email, LocalDate birthday, int point, String profileUrl) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.point = point;
        // TODO : 실제 배포시에는 Role.ROLE_USER 로 바꿀것!
        this.role = Role.ROLE_ADMIN;
        this.profileUrl = profileUrl;
    }

    /**
     * 파라미터로부터 멤버 엔티티 객체를 생성하는 함수.
     */
    public static Member of(String memberId,String password, String name, String email, LocalDate birthday, int point, String profileUrl) {
        return new Member(memberId, password, name, email, birthday, point, profileUrl);
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

    public void updatePassword(String encryptPassword) {
        this.password = encryptPassword;
    }

    public void updateName(String name) {
        this.name = name;
    }
}


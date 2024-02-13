package com.otc.tinyclassroom.member.entity;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;

/**
 * ClassRoom과 Member의 ManyToMany를 해결하기 위한 객체.
 */
@Entity
@Getter
@Table(name = "classroom_member")
public class ClassRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private ClassRoom classRoom;

    @ManyToOne(fetch = LAZY)
    private Member member;

    protected ClassRoomMember() {
    }

    private ClassRoomMember(ClassRoom classRoom, Member member) {
        this.classRoom = classRoom;
        this.member = member;
    }

    public static ClassRoomMember of(ClassRoom classRoom, Member member) {
        return new ClassRoomMember(classRoom, member);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassRoomMember that)) {
            return false;
        }
        return Objects.equals(classRoom, that.classRoom) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classRoom, member);
    }
}



package com.otc.tinyclassroom.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * member ClassRomm 맵핑 entity.
 */
@Getter
@Entity
public class MemberClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @Setter
    @ManyToOne
    private ClassRoom classRoom;

    protected MemberClassRoom() {
    }

    private MemberClassRoom(Member member, ClassRoom classRoom) {
        this.member = member;
        this.classRoom = classRoom;
    }

    public static MemberClassRoom of(Member member, ClassRoom classRoom) {
        return new MemberClassRoom(member, classRoom);
    }
}

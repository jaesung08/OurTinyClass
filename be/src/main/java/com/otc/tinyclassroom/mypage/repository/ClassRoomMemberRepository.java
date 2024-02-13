package com.otc.tinyclassroom.mypage.repository;

import com.otc.tinyclassroom.member.entity.ClassRoomMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 같은 반 학생 조회를 위한 레포지토리.
 */
public interface ClassRoomMemberRepository extends JpaRepository<ClassRoomMember, Long> {
    List<ClassRoomMember> findByMemberId(Long memberId);

}
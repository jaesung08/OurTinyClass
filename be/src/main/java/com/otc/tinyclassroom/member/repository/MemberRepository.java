package com.otc.tinyclassroom.member.repository;

import com.otc.tinyclassroom.member.entity.ClassRoom;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 회원 레포지토리.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 회원 아이디 검색.
     */
    Optional<Member> findByMemberId(String memberId);

    /**
     * 역할과 클래스룸을 파라메터로 멤버 검색.
     */
    Optional<Member> findByRoleAndClassRoom(Role role, ClassRoom classRoom);
}
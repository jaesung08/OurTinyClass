package com.otc.tinyclassroom.member.repository;

import com.otc.tinyclassroom.member.entity.Member;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 회원 Repository.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원 아이디 검색
    Optional<Member> findByMemberId(String memberId);

    // 삭제된 유저까지도 아이디 겹치는지 확인
    Optional<Member> findByMemberIdAndDeletedAtIsNotNull(String memberId);

}
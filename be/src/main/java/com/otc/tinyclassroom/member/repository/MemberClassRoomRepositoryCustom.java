package com.otc.tinyclassroom.member.repository;

import com.otc.tinyclassroom.member.entity.ClassRoom;
import com.otc.tinyclassroom.member.entity.Member;
import java.util.List;

/**
 * Member관련 QueryDSL 작성을 위한 Interface.
 */
public interface MemberClassRoomRepositoryCustom {

    List<Member> findMemberByClassRoomId(Long classRoomId);

    List<ClassRoom> findClassRoomByMemberId(Long memberId);
}

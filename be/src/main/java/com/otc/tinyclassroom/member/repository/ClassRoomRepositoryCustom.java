package com.otc.tinyclassroom.member.repository;

import com.otc.tinyclassroom.member.entity.ClassRoom;
import java.util.List;

/**
 * Member관련 QueryDSL 작성을 위한 Interface.
 */
public interface ClassRoomRepositoryCustom {

    List<ClassRoom> findClassRoomByGradeAndYearWithNonZeroNumber(int grade, int year);

}

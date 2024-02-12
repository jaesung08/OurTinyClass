package com.otc.tinyclassroom.member.repository;

import com.otc.tinyclassroom.member.entity.ClassRoom;
import com.otc.tinyclassroom.member.entity.QClassRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * custom Repository 구현.
 */
@Repository
@AllArgsConstructor
@SuppressWarnings("checkstyle:LocalVariableName")
public class ClassRoomRepositoryCustomImpl implements ClassRoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ClassRoom> findClassRoomByGradeAndYearWithNonZeroNumber(int grade, int year) {
        QClassRoom classRoom = QClassRoom.classRoom;
        return queryFactory.selectFrom(classRoom)
            .where(classRoom.grade.eq(grade)
                    .and(classRoom.year.eq(year))
                    .and(classRoom.number.gt(0)))
            .fetch();
    }
}

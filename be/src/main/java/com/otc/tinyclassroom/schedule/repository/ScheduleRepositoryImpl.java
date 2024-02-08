package com.otc.tinyclassroom.schedule.repository;

import static com.otc.tinyclassroom.lecture.entity.QLecture.lecture;
import static com.otc.tinyclassroom.member.entity.QMember.member;
import static com.otc.tinyclassroom.schedule.entity.QSchedule.schedule;

import com.otc.tinyclassroom.schedule.dto.ScheduleCheckDto;
import com.otc.tinyclassroom.schedule.dto.ScheduleListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 * Schedule 관련 레포지토리.
 */
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManagerFactory entityManagerFactory;

    /**
     * 원하는 날짜의 해당 멤버 스케줄을 주 단위로 반환.
     *
     * @param memberId memberId 멤버 아이디.
     * @param start    주의 시작일인 월요일의 날짜.
     * @return 스케줄 리스트. 날짜 오름차, 교시 오름차 순으로 정렬.
     */
    @Override
    public List<ScheduleListDto> findScheduleListByMemberId(String memberId, LocalDate start) {
        // 주의 종료일. 월요일 + 4 = 금요일
        LocalDate end = start.plusDays(4);

        List<ScheduleListDto> execute = jpaQueryFactory
            // 생성자를 이용해 퀴리 반환값 tuple을 scheduleResponseDto 리스트로 바로 매핑.
            .select(Projections.constructor(ScheduleListDto.class,
                lecture.id,
                schedule.id,
                lecture.title,
                lecture.lectureType,
                lecture.lectureCategoryType,
                schedule.scheduleDate,
                lecture.dayOfWeek,
                lecture.timeTable,
                schedule.deletable
            ))
            .from(schedule)
            .join(schedule.lecture, lecture)
            .join(schedule.member, member)
            .where(
                // 스케줄 memberId와 현재 유저의 id 값이 같고
                member.memberId.eq(memberId),
                // scheduleDate가 start와 end 사이 안에 있고
                schedule.scheduleDate.between(start, end),
                // 소프트 딜리트 되지 않은 경우
                schedule.deletedAt.isNull()
            )
            .orderBy(
                schedule.scheduleDate.asc(),
                lecture.timeTable.asc()
            )
            .fetch();

        return execute;
    }

    /**
     * 스케줄 삽입 메서드.
     *
     * @return 스케줄 삽입 여부. 정상 실행 시 1 반환.
     */
    @Override
    public long insertSchedule() {

        // TODO : 스케줄 삽입 로직.

        return 0;
    }

    /**
     * 스케줄 삭제 메서드.
     * 소프트 딜리트 적용.
     *
     * @param id 지우고자 하는 스케줄의 pk.
     * @return 지운 스케줄 갯수. 정상 삭제 시 1.
     */
    @Override
    public long deleteScheduleById(Long id) {
        return jpaQueryFactory
                .update(schedule)
                .set(schedule.deletedAt, LocalDateTime.now())
                .where(
                        schedule.id.eq(id),
                        schedule.deletedAt.isNull()
                )
                .execute();
    }

    /**
     * 주어진 pk에 해당하는 스케줄 반환 메서드.
     *
     * @param id 스케줄 pk.
     * @return 스케줄 dto 반환.
     */
    @Override
    public Optional<ScheduleCheckDto> findScheduleById(Long id) {
        return Optional.ofNullable(
            jpaQueryFactory
                .select(Projections.constructor(ScheduleCheckDto.class,
                    schedule.id,
                    member.id,
                    schedule.scheduleDate,
                    lecture.dayOfWeek,
                    lecture.timeTable,
                    schedule.deletable
                ))
                .from(schedule)
                .join(schedule.lecture, lecture)
                .join(schedule.member, member)
                .where(
                    schedule.id.eq(id),
                    schedule.deletedAt.isNull()
                )
                .fetchOne()
        );
    }

    /**
     * pk에 해당하는 멤버가 해당 날짜와 교시에 수강하고 있는 스케줄을 반환하는 메서드.
     * insert 이전에 해당 날짜와 교시에 수강하는 강의가 있는지 확인하기 위한 용도.
     *
     * @param memberId     멤버 pk.
     * @param scheduleDate 스케줄의 날짜.
     * @param timeTable    스케줄의 교시.
     * @return 스케줄 Dto.
     */
    @Override
    public Optional<ScheduleCheckDto> findScheduleByMemberIdAndScheduleDateAndTimeTable(Long memberId, LocalDate scheduleDate, Integer timeTable) {
        return Optional.ofNullable(
            jpaQueryFactory
                .select(Projections.constructor(ScheduleCheckDto.class,
                    schedule.id,
                    member.id,
                    schedule.scheduleDate,
                    lecture.dayOfWeek,
                    lecture.timeTable,
                    schedule.deletable
                ))
                .from(schedule)
                .join(schedule.lecture, lecture)
                .join(schedule.member, member)
                .where(
                    member.id.eq(memberId),
                    schedule.scheduleDate.eq(scheduleDate),
                    lecture.timeTable.eq(timeTable),
                    schedule.deletedAt.isNull()
                )
                .fetchOne()
        );
    }
}

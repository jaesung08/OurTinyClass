<<<<<<< PATCH SET (3b3d53 feat: #122 - 시간표 서비스 구현)
package com.otc.tinyclassroom.lecture.entity;

import com.otc.tinyclassroom.lecture.entity.type.LectureCategoryType;
import com.otc.tinyclassroom.lecture.entity.type.LectureType;
import com.otc.tinyclassroom.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.Getter;

/**
 * 강의 엔티티 정의.
 */
@Getter
@Entity
@Table
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private int dayOfWeek;

    @NotNull
    private int timeTable;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LectureType lectureType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LectureCategoryType lectureCategoryType;

    private String lectureUrl;

    protected Lecture() {

    }

    /**
     * Lecture 기본 생성자.
     */
    private Lecture(Member member, String title, String description, int dayOfWeek, int timeTable, LectureType lectureType, LectureCategoryType lectureCategoryType, String lectureUrl) {
        this.member = member;
        this.title = title;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.timeTable = timeTable;
        this.lectureType = LectureType.SPECIAL_LECTURE;
        this.lectureCategoryType = lectureCategoryType;
        this.lectureUrl = lectureUrl;
    }

    /**
     * Lecture Entity 생성 메서드.
     */
    public static Lecture of(Member member, String title, String description, int dayOfWeek, int timeTable, LectureType lectureType, LectureCategoryType lectureCategoryType, String lectureUrl) {
        return new Lecture(member, title, description, dayOfWeek, timeTable, lectureType, lectureCategoryType, lectureUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lecture lecture)) {
            return false;
        }
        return id != null && id.equals(lecture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
=======
>>>>>>> BASE      (731819 Merge branch 'backend/feature/#102-image-server' into 'be')

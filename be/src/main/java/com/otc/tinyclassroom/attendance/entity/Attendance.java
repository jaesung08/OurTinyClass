package com.otc.tinyclassroom.attendance.entity;

import com.otc.tinyclassroom.attendance.entity.type.AttendanceStatus;
import com.otc.tinyclassroom.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

/**
 * 출석관련 엔티티 정의 (member, checkIn, checkOut, status).
 *
 * @author JangJaeSung
 */
@Getter
@ToString
@Table(name = "attendance")
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //db 번호

    @ManyToOne(optional = false)
    @Exclude
    private Member member; // 유저 id

    @Column
    private Timestamp checkIn; // 등교 시간

    @Column
    private Timestamp checkOut; // 하교 시간

    @Column(nullable = false)   // enum 클래스 사용시 자동으로 enum 조건만큼 제약조건이 걸리게된다. 수정시엔 DB다시 create
    private AttendanceStatus status; // 정상등교, 지각 여부 // SELECT * FROM information_schema.check_constraints; 제약조건 확인 쿼리문


    protected Attendance() {
    }

    /**
     * 파라미터 생성자.
     */
    private Attendance(Member member, Timestamp checkIn, Timestamp checkOut, AttendanceStatus status) {
        this.member = member;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    /**
     * 파라미터로 부터 attendance 객체를 생성하는 함수.
     */
    public static Attendance of(Member member, Timestamp checkIn, Timestamp checkOut, AttendanceStatus status) {
        return new Attendance(member, checkIn, checkOut, status);
    }

    /**
     * checkout 시 수정하는 함수.
     */
    public void updateCheckOutTime(Timestamp timestamp, AttendanceStatus status) {
        if (timestamp != null && status != null) {
            this.checkOut = timestamp;
            this.status = status;
        }
    }
}

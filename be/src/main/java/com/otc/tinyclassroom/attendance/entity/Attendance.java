package com.otc.tinyclassroom.attendance.entity;

import com.otc.tinyclassroom.member.entity.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import lombok.ToString.Exclude;

@Getter
@Setter
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

    @Column(nullable = false)
    private AttendanceStatus status; // 정상등교, 지각 여부

    protected Attendance() {
    }
    /**
     * 파라미터 생성자
     */
    private Attendance(Member member, Timestamp checkIn, Timestamp checkOut, AttendanceStatus status) {
        this.member = member;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }
    /**
     * 파라미터로 부터 attendance 객체를 생성하는 함수
     */
    public static Attendance of(Member member, Timestamp checkIn, Timestamp checkOut, AttendanceStatus status) {
        return new Attendance(member, checkIn, checkOut, status);
    }


}

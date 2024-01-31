package com.otc.tinyclassroom.member.entity;

import com.otc.tinyclassroom.global.common.StringListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

/**
 * Student ROLE 업데이트 요청 엔티티.
 */
@Getter
@Table(name = "student_role_update_request")
@Entity
public class StudentRoleUpdateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    // Student의 등업신청 속성
    private int beforeSchoolType;
    private int quitReason;

    @Convert(converter = StringListConverter.class)
    private List<String> originalFileNames;

    @Convert(converter = StringListConverter.class)
    private List<String> quitConfirmationPaths; // 파일 경로들을 저장하는 리스트

    private StudentRoleUpdateRequest(Member member, String content, int beforeSchoolType, int quitReason, List<String> originalFileNames, List<String>  quitConfirmationPaths) {
        this.member = member;
        this.content = content;
        this.beforeSchoolType = beforeSchoolType;
        this.quitReason = quitReason;
        this.originalFileNames = originalFileNames;
        this.quitConfirmationPaths = quitConfirmationPaths;
    }

    protected StudentRoleUpdateRequest() {
    }

    public static StudentRoleUpdateRequest of(Member member, String content, int beforeSchoolType, int quitReason, List<String> originalFileNames, List<String> quitConfirmationPaths) {
        return new StudentRoleUpdateRequest(member, content, beforeSchoolType, quitReason, originalFileNames, quitConfirmationPaths);
    }
}

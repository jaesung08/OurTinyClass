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
 * Mentor ROLE 업데이트 요청 엔티티.
 */
@Getter
@Table(name = "mentor_role_update_request")
@Entity
public class MentorRoleUpdateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    // Mentor 의 등업신청 속성
    private String belong;

    @Convert(converter = StringListConverter.class)
    private List<String> originalFileNames;

    @Convert(converter = StringListConverter.class)
    private List<String> belongDocumentPaths; // 파일 경로들을 저장하는 리스트

    /**
     * 생성자 메서드.
     */
    private MentorRoleUpdateRequest(Member member, String content, String belong, List<String> originalFileNames, List<String> belongDocumentPaths) {
        this.member = member;
        this.content = content;
        this.belong = belong;
        this.originalFileNames = originalFileNames;
        this.belongDocumentPaths = belongDocumentPaths;
    }

    protected MentorRoleUpdateRequest() {
    }

    public static MentorRoleUpdateRequest of(Member member, String content, String belong, List<String> originalFileNames, List<String> belongDocumentPaths) {
        return new MentorRoleUpdateRequest(member, content, belong, originalFileNames, belongDocumentPaths);
    }
}

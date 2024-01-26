package com.otc.tinyclassroom.member.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otc.tinyclassroom.global.config.WebSecurityConfig;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.exception.MemberErrorCode;
import com.otc.tinyclassroom.member.exception.MemberException;
import com.otc.tinyclassroom.member.service.MemberService;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("member controller 테스트")
@Import(WebSecurityConfig.class)
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;

    public MemberControllerTest(@Autowired MockMvc mvc, @Autowired ObjectMapper objectMapper) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
    }

    @DisplayName("[POST] 회원 가입 성공")
    @Test
    void givenInfo_whenRequestJoin_thenReturnSuccess() throws Exception {
        // Given
        MemberJoinRequestDto request = MemberJoinRequestDto.of("test", "test1@Q", "test", "test@gmail.com", LocalDate.now());
        doNothing().when(memberService).join(any(MemberJoinRequestDto.class));

        // When
        mvc.perform(post("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
            .andExpect(status().isOk());

        // Then
        then(memberService).should().join(any(MemberJoinRequestDto.class));
    }

    @DisplayName("[POST] 빈 필드 - 회원가입 실패")
    @Test
    void givenBlankField_whenRequestJoin_thenThrowInvalidFieldValueException() throws Exception {
        // Given
        MemberJoinRequestDto request = MemberJoinRequestDto.of("test", null, "test", "test@gmailcom", LocalDate.now());
        doThrow(new MemberException(MemberErrorCode.INVALID_FIELD_VALUE))
            .when(memberService)
            .join(any(MemberJoinRequestDto.class));

        // When & then
        mvc.perform(post("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
            .andExpect(status().isBadRequest());
    }

    @DisplayName("[POST] 유효하지 않은 아이디 - 회원가입 실패")
    @Test
    void givenInvalidMemberId_whenRequestJoin_thenThrowInvalidMemberIdException() throws Exception {
        // Given
        MemberJoinRequestDto invalidEmail = MemberJoinRequestDto.of("test", "test", "test", "test@gmail.com", LocalDate.now());
        doThrow(new MemberException(MemberErrorCode.INVALID_MEMBER_ID))
            .when(memberService)
            .join(any(MemberJoinRequestDto.class));

        // When & then
        mvc.perform(post("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(invalidEmail)))
            .andExpect(status().isBadRequest());
    }

    @DisplayName("[POST] 중복된 아이디 - 회원가입 실패")
    @Test
    void givenDuplicatedMemberId_whenRequestJoin_thenThrowDuplicatedException() throws Exception {
        // Given
        MemberJoinRequestDto duplicate = MemberJoinRequestDto.of("test", "test", "test", "test@gmail.com", LocalDate.now());
        doThrow(new MemberException(MemberErrorCode.DUPLICATED_USER_NAME))
            .when(memberService)
            .join(any(MemberJoinRequestDto.class));

        // When & then
        mvc.perform(post("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(duplicate)))
            .andExpect(status().isConflict());
    }

    @DisplayName("[POST] 유효하지 않은 비밀번호 - 회원가입 실패")
    @Test
    void givenInvalidPassword_whenRequestJoin_thenThrowPasswordValidationFailedException() throws Exception {
        // Given
        MemberJoinRequestDto invalidEmail = MemberJoinRequestDto.of("test", "test", "test", "test@gmail.com", LocalDate.now());
        doThrow(new MemberException(MemberErrorCode.PASSWORD_VALIDATION_FAILED))
            .when(memberService)
            .join(any(MemberJoinRequestDto.class));

        // When & then
        mvc.perform(post("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(invalidEmail)))
            .andExpect(status().isBadRequest());
    }


    @DisplayName("[POST] 유효하지 않은 이메일 - 회원가입 실패")
    @Test
    void givenInvalidEmail_whenRequestJoin_thenThrowInvalidEmailException() throws Exception {
        // Given
        MemberJoinRequestDto invalidEmail = MemberJoinRequestDto.of("test", "test", "test", "test@gmail.com", LocalDate.now());
        doThrow(new MemberException(MemberErrorCode.INVALID_EMAIL))
            .when(memberService)
            .join(any(MemberJoinRequestDto.class));

        // When & then
        mvc.perform(post("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(invalidEmail)))
            .andExpect(status().isBadRequest());
    }

}
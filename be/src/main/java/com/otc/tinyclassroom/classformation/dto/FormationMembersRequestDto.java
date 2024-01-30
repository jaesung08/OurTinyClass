package com.otc.tinyclassroom.classformation.dto;

import java.util.List;

/**
 * 다수 멤버 반 편성 request 를 위한 Dto.
 */
public record FormationMembersRequestDto(List<Long> memberIds, Long classRoomId) {

}

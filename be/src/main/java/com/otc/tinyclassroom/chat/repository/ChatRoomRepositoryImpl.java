package com.otc.tinyclassroom.chat.repository;

import static com.otc.tinyclassroom.chat.entity.QChatMessage.chatMessage;
import static com.otc.tinyclassroom.chat.entity.QChatRoom.chatRoom;
import static com.otc.tinyclassroom.chat.entity.QChatRoomMember.chatRoomMember;
import static com.otc.tinyclassroom.member.entity.QMember.member;

import com.otc.tinyclassroom.chat.dto.ChatRoomDto;
import com.otc.tinyclassroom.chat.dto.RoomMemberDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 채팅룸 repository 구현체.
 */
@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChatRoomDto> findAllChatRoomByMemberId(Long userId) {

        return jpaQueryFactory
                .select(Projections.constructor(
                        ChatRoomDto.class,
                        chatRoom.id,
                        chatMessage.id,
                        chatMessage.message
                ))
                .from(chatRoomMember)
                .join(chatRoomMember.chatRoom, chatRoom)
                .leftJoin(chatRoom.lastChatMessage, chatMessage)
                .where(chatRoomMember.member.id.eq(userId))
                .fetch();
    }

    @Override
    public List<RoomMemberDto> findAllRoomMemberByRoomId(String roomId) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        RoomMemberDto.class,
                        member.id,
                        member.memberId,
                        member.name,
                        member.email
                ))
                .from(chatRoomMember)
                .join(chatRoomMember.member, member)
                .where(chatRoomMember.chatRoom.id.eq(roomId))
                .fetch();
    }
}

package com.otc.tinyclassroom.chat.controller;

import com.otc.tinyclassroom.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

/**
 * chat 기능을 위한 controller
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    //    private SimpMessagingTemplate
    // 특정 브로커로 메세지 전달.
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 클라이언트가 /pub/chat 으로 메세지를 발행.
     */
    @MessageMapping("/chat")
    public void message(ChatMessageDto chatMessageDto) {
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + chatMessageDto.getChannelId(), chatMessageDto.getContent());
        log.info("Room : {}, sender : {}, message : {}", chatMessageDto.getChannelId(), chatMessageDto.getSender(), chatMessageDto.getContent());
    }
}

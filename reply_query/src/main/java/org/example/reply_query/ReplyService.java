package org.example.reply_query;

import lombok.extern.slf4j.Slf4j;
import org.example.reply_query.model.Reply;
import org.example.reply_query.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.example.reply_query.model.ReplyEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReplyService {
    private final ReplyRepository replyRepository;

    @KafkaListener(topics = "reply", groupId = "reply-group-id",
            // 내가 받는 Dto의 타입이 어떤 타입인지 지정
            properties = "spring.json.value.default.type:org.example.reply_query.model.ReplyEvent.Create")

    public void consume(
            @Header(KafkaHeaders.RECEIVED_KEY) Long key,
            @Payload ReplyEvent.Create dto
    ){
        log.debug("MassageConsumer - consume : {}={}", key, dto.toString());
    }

    public ReplyDto.Replyres read(Long idx) {
        Reply reply = replyRepository.findById(idx).orElseThrow();
        return ReplyDto.Replyres.from(reply);
    }

}

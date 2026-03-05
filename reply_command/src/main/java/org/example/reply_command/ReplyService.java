package org.example.reply_command;

import org.example.reply_command.model.Reply;
import org.example.reply_command.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.example.reply_command.model.ReplyEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final KafkaTemplate<Long, ReplyEvent.Create> kafkaTemplate;

   public ReplyDto.ReplyRes reg(Long boardIdx, Long userIdx,String userName,ReplyDto.ReplyReq dto) {
       Reply reply = replyRepository.save(dto.toEntity(userIdx, boardIdx, userName));

       kafkaTemplate.send("reply", reply.getIdx(), ReplyEvent.Create.from(reply));

        return ReplyDto.ReplyRes.from(reply);
   }
}

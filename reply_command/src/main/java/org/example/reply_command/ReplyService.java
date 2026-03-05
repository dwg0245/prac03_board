package org.example.reply_command;

import org.example.reply_command.model.Reply;
import org.example.reply_command.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

   public ReplyDto.ReplyRes reg(Long boardIdx, Long userIdx,String userName,ReplyDto.ReplyReq dto) {
        Reply reply = replyRepository.save(dto.toEntity(userIdx, boardIdx, userName));

        return ReplyDto.ReplyRes.from(reply);
   }
}

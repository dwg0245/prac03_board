package org.example.reply_query;

import org.example.reply_query.model.Reply;
import org.example.reply_query.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyDto.Replyres read(Long idx) {
        Reply reply = replyRepository.findById(idx).orElseThrow();
        return ReplyDto.Replyres.from(reply);
    }

}

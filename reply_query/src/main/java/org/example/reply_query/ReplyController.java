package org.example.reply_query;

import org.example.board_core.common.model.BaseResponse;
import org.example.reply_query.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reply")
@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping("/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx){
        ReplyDto.Replyres result = replyService.read(idx);
        return ResponseEntity.ok(BaseResponse.success(result));
    }
}

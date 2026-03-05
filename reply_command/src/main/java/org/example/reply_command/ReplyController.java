package org.example.reply_command;

import org.example.reply_command.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.board_core.common.model.BaseResponse;

@RequestMapping("/reply")
@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/reg/{boardIdx}")
    public ResponseEntity reg(
            @RequestHeader(name = "X-User_Idx") Long userIdx,
            @RequestHeader(name = "X-User_Name") String userName,
            @PathVariable Long boardIdx ,
            @RequestBody ReplyDto.ReplyReq dto){
        ReplyDto.ReplyRes result = replyService.reg(boardIdx, userIdx, userName, dto);

        return ResponseEntity.ok(BaseResponse.success(result));
    };



}

package org.example.board_query;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.board_core.common.model.BaseResponse;
import org.example.board_query.model.BoardDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
@Tag(name="게시판 기능")
public class BoardController {
    private final BoardService boardService;

    // 패이지 번호는 0번 부터/ 몇개씩 잘라서 보겠다.
    @GetMapping("/list")
    public ResponseEntity list(
            // 기본값을 설정해주기, 반드시 존재, 기본 설정값
            @RequestParam(required = true, defaultValue = "0") int page,
            @RequestParam(required = true, defaultValue = "5")int size) {
        //List<BoardDto.ListRes> dto = boardService.list(page,size);
        BoardDto.PageRes dto = boardService.list(page,size);
        return ResponseEntity.ok(BaseResponse.success(dto));
    }

    @GetMapping("/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx) {
        BoardDto.ReadRes dto = boardService.read(idx);
        return ResponseEntity.ok(BaseResponse.success(dto));
    }

}


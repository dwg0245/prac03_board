package org.example.board_query;

import org.example.board_query.model.Board;
import org.example.board_query.model.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardDto.PageRes list(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);

        // 페이징 처리 O, 페이지 번호가 필요하다 => Page로 반환
        // 페이징 처리 O, 페이지 번호가 필요없다 => Slice 반환, 다음 사이즈의 크기만큼 가져온다.
        // Slice<Board> result = boardRepository.findAll(pageRequest); // 무한스크롤
        Page<Board> result = boardRepository.findAll(pageRequest);

        return BoardDto.PageRes.from(result);
    }

    public BoardDto.ReadRes read(Long idx) {
        Board board = boardRepository.findById(idx).orElseThrow();
        return BoardDto.ReadRes.from(board);
    }

}

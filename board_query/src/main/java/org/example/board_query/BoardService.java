package org.example.board_query;



import lombok.RequiredArgsConstructor;
import org.example.board_query.model.Board;
import org.example.board_query.model.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.example.board_query.model.BoardEvent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//    BFF			여러 서비스로 데이터를 가져와서 통합시켜서 응답을 주는 백엔드를 하나 더 만드는 방식
//                  데이터 정합성이 중요할 때
//    카프카 캐싱		이벤트가 발생했을 때 해당 데이터가 필요한 특정 서비스들이 자기 서버에 직접 저장해두는 방식
//                  정합성보다는 속도가 중요할 때
//    프론트 병렬		프론트엔드에서 필요한 서비스들을 각각 호출하는 방식
//                  부하를 클라이언트에게 전가하기
//    GraphQL		일반적인 HTTP 요청이 아닌 GraphQL이라는 형식으로 데이터를 요청하고 응답하는 방식
//                  조합해야하는 서비스가 많을 때


@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    // 카프카로부터 메세지를 받아서 DB에 데이터 저장 query
    @KafkaListener(topics = "board", groupId = "board-group-1",
            properties = "spring.json.value.default.type:org.example.board_query.model.BoardEvent.Create")

    public void consume(
            @Header(KafkaHeaders.RECEIVED_KEY) long key,
            @Payload BoardEvent.Create dto
    ){
        log.debug("MessageConsumer - consume : {}={}",key,dto.toString());
        // idx가 있는걸로 수면 수정이 되어 버린다.
        boardRepository.save(dto.toEntity());
    }


    public BoardDto.PageRes list(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        // 페이징 처리 ⭕, 페이지 번호가 필요하다 => Page 반환
        // 페이징 처리 ⭕, 페이지 번호가 필요없다. => Slice 반환
        Page<Board> result = boardRepository.findAll(pageRequest);

        return BoardDto.PageRes.from(result);
    }

    public BoardDto.ReadRes read(Long idx) {
        Board board = boardRepository.findById(idx).orElseThrow();
        return BoardDto.ReadRes.from(board);
    }
}
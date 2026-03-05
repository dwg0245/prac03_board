package org.example.board_query.model;

import lombok.*;

import java.time.LocalDateTime;

public class BoardEvent {

    @Getter
    @ToString
    public static class Create{
        private String uuid;            // 고유키
        private String eventType;       // 어떤 이벤트가 발생을 했는지
        private LocalDateTime issueAt;  // 발생 시간
        private Long idx;
        private String title;
        private String contents;
        private Long user_idx;
        private String user_name;

        public Board toEntity(){
            return Board.builder()
                    .idx(this.idx)
                    .title(this.title)
                    .contents(this.contents)
                    .userIdx(this.user_idx)
                    .userName(this.user_name)
                    .build();
        }

    }
}

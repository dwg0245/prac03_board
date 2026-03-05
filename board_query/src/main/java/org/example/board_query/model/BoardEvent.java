package org.example.board_query.model;

import lombok.*;

import java.time.LocalDateTime;

public class BoardEvent {

    @Getter
    @ToString
    public static class Create{
        private String uuid;
        private String eventType;
        private LocalDateTime issueAt;
        private Long idx;
        private String title;
        private String contents;

        public Board toEntity(){
            return Board.builder()
                    .idx(this.idx)
                    .title(this.title)
                    .contents(this.contents)
                    .build();
        }

    }
}

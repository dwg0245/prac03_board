package org.example.reply_query.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReplyDto {

    @Builder
    @Getter
    public static class Replyres{
        private Long idx;
        private String contents;
        private String writer;
        private Long boardIdx;
        private Long userIdx;

        public static Replyres from(Reply entity){
            return Replyres.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUser_name())
                    .boardIdx(entity.getBoard_idx())
                    .userIdx(entity.getUser_idx())
                    .build();
        }
    }
}

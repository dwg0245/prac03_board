package org.example.reply_command.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ReplyDto {
    @Getter
    public static class ReplyReq {
        private String contents;

        public Reply toEntity(Long userIdx, Long boardIdx, String userName) {
            return Reply.builder()
                    .contents(contents)
                    .user_idx(userIdx)
                    .board_idx(boardIdx)
                    .user_name(userName)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ReplyRes {
        private Long idx;
        private String contents;
        private String writer;
        private Long board_idx;

        public static ReplyDto.ReplyRes from(Reply entity) {
            return ReplyRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUser_name())
                    .board_idx(entity.getBoard_idx())
                    .build();
        }
    }
}
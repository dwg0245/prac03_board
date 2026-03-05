package org.example.reply_command.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReplyEvent {

    @Getter
    @Builder
    public static class Create{
        private String uuid;            // 고유키
        private String eventType;       // 어떤 이벤트가 발생을 했는지
        private LocalDateTime issueAt;  // 발생 시간
        private Long idx;
        private String contents;
        private Long user_idx;
        private String user_name;

        public static Create from(Reply entity) {
            return Create.builder()
                    .uuid(UUID.randomUUID().toString())
                    .eventType("CREATE")
                    .issueAt(LocalDateTime.now())
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .user_idx(entity.getIdx())
                    .user_name(entity.getUser_name())
                    .build();
        }
    }
}

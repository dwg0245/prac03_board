package org.example.board_query.model;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

public class BoardDto {
    @Getter
    @Builder
    // 페이지 응답 dto 만들기
    public static class PageRes{
        private List<ListRes> boardList;
        private int totalPage;
        private long totalCount;
        private int currentPage;
        private int currentSize;

        public static PageRes from(Page<Board> result){
            return PageRes.builder()
                    .boardList(result.get().map(ListRes::from).toList())
                    .totalPage(result.getTotalPages())
                    .totalCount(result.getTotalElements())
                    .currentPage(result.getPageable().getPageNumber())
                    .currentSize(result.getPageable().getPageSize())
                    .build();
        }
    }


    @Builder
    @Getter
    public static class ListRes {
        private Long idx;
        private String title;
        private String writer;
        private long replySize;
        private int likes;


        public static ListRes from(Board entity) {
            return ListRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .writer(entity.getUserName())
                    .build();
        }
    }


    @Builder
    @Getter
    public static class ReadRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;
        private int likesCount;

        public static ReadRes from(Board entity) {
            return ReadRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .writer(entity.getUserName())
                    .build();
        }
    }
}

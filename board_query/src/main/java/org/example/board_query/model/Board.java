package org.example.board_query.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
    @Id
    // Board_command 서비스에서 저장한 내용 그대로 저장하기 위해서 AUTO_INCREMENT 설정을 제거
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable=false, length = 100)
    private String title;
    private String contents;

    Long userIdx;
    String userName;
}

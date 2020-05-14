package org.zerock.board1.dto;

import lombok.*;
import org.zerock.board1.domain.Board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardDTO {

    private Long bno;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private String regDateStr;

    private String modDateStr;

    public BoardDTO(Board board){
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.regDate = board.getRegDate();
        this.modDate = board.getModDate();

        this.regDateStr = regDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.modDateStr = modDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public Board getEntity(){
        return Board.builder()
                .bno(this.bno)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .build();
    }

}

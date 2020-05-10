package org.zerock.board1.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.board1.domain.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class BoardPageDTO {

    private List<BoardDTO> boardList;

    private Pageable pageInfo;

    private int page;

    private int amount;

    private int totalPage;

    public BoardPageDTO(Page<Board> result){

        boardList = result.getContent().stream().map(board -> new BoardDTO(board)).collect(Collectors.toList());

        pageInfo = result.getPageable();

        page = result.getNumber();

        amount = result.getSize();

        totalPage = result.getTotalPages();
    }
}

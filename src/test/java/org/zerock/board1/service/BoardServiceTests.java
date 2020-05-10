package org.zerock.board1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.board1.domain.Board;
import org.zerock.board1.dto.BoardDTO;
import org.zerock.board1.dto.BoardPageDTO;

@SpringBootTest

public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){

        BoardDTO dto = BoardDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .writer("USER00")
                .build();

        System.out.println("Test Result.....");
        boardService.register(dto);

    }

    @Test
    public void testGet(){

        BoardDTO dto = boardService.get(1L);

        System.out.println(dto);
    }

    @Test
    public void testPaging(){

        Pageable pageable = PageRequest.of(0,10);

        BoardPageDTO pageDTO = boardService.getPage(pageable);

        System.out.println(pageDTO);

    }


}




















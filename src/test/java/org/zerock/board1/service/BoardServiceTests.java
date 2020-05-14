package org.zerock.board1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.board1.dto.BoardDTO;
import org.zerock.board1.dto.BoardPageDTO;
import org.zerock.board1.dto.SearchDTO;

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

        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC, "bno");

        BoardPageDTO pageDTO = boardService.getPage(pageable);

        System.out.println(pageDTO);

        for (BoardDTO boardDTO : pageDTO.getBoardList()) {
            System.out.println(boardDTO);
        }

    }

    @Test
    public void testUpdate(){

        BoardDTO boardDTO = BoardDTO.builder().bno(1L).title("new Title").content("new Content").build();

        boardService.modify(boardDTO);
    }

    @Test
    public void testSearch(){
        SearchDTO searchDTO = new SearchDTO("tc","10");

        Pageable pageable = PageRequest.of(0,10);

        BoardPageDTO pageDTO = boardService.searchPage(searchDTO, pageable);

        System.out.println(pageDTO);
    }


}




















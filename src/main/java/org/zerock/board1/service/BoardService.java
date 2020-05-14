package org.zerock.board1.service;

import org.springframework.data.domain.Pageable;
import org.zerock.board1.dto.BoardDTO;
import org.zerock.board1.dto.BoardPageDTO;
import org.zerock.board1.dto.SearchDTO;

public interface BoardService {

    void register(BoardDTO dto);

    BoardDTO get(Long bno);

    BoardPageDTO getPage(Pageable pageable);

    void remove(Long bno);

    void modify(BoardDTO dto);

    BoardPageDTO searchPage(SearchDTO searchDTO, Pageable pageable);

}

package org.zerock.board1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.board1.domain.Board;
import org.zerock.board1.dto.BoardDTO;
import org.zerock.board1.dto.BoardPageDTO;

public interface BoardService {

    void register(BoardDTO dto);

    BoardDTO get(Long bno);

    BoardPageDTO getPage(Pageable pageable);
}

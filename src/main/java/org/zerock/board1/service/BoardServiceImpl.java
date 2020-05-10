package org.zerock.board1.service;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.board1.domain.Board;
import org.zerock.board1.dto.BoardDTO;
import org.zerock.board1.dto.BoardPageDTO;
import org.zerock.board1.repository.BoardRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
@Log4j2
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    private BoardRepository repository;

    @Override
    public void register(BoardDTO dto) {

        Board board = dto.getEntity();

        log.info("------------------------");
        log.info(dto);

        log.info("=========================");
        log.info(board);

        repository.save(board);
    }

    @Override
    public BoardDTO get(Long bno) {
        Optional<Board> result = repository.findById(bno);
        return result.isPresent()? new BoardDTO(result.get()):null;
    }

    @Override
    public BoardPageDTO getPage(Pageable pageable) {
        return new BoardPageDTO(repository.findAll(pageable));
    }


}

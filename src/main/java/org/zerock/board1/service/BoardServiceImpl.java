package org.zerock.board1.service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.board1.domain.Board;
import org.zerock.board1.domain.QBoard;
import org.zerock.board1.dto.BoardDTO;
import org.zerock.board1.dto.BoardPageDTO;
import org.zerock.board1.dto.SearchDTO;
import org.zerock.board1.repository.BoardRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

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

    @Override
    public void remove(Long bno) {

        repository.deleteById(bno);
    }

    @Override
    public void modify(BoardDTO dto) {

        Optional<Board> result = repository.findById(dto.getBno());

        if(result.isPresent()){

            Board board = result.get();

            board.changeTitle(dto.getTitle());
            board.changeContent(dto.getContent());

            repository.save(board);
        }
    }

    @Override
    public BoardPageDTO searchPage(SearchDTO searchDTO, Pageable pageable) {

        String keyword = searchDTO.getKeyword();
        String[] typeArr = searchDTO.getType().split("");

        BooleanBuilder builder = new BooleanBuilder();

        QBoard qBoard = QBoard.board;

        BooleanBuilder innerBuilder = new BooleanBuilder();

        Arrays.stream(typeArr).forEach(word -> {

            if(word.equals("t")){
                BooleanExpression expression = qBoard.title.contains(keyword);
                innerBuilder.or(expression);
            }
            if(word.equals("c")){
                BooleanExpression expression = qBoard.content.contains(keyword);
                innerBuilder.or(expression);
            }
            if(word.equals("w")){
                BooleanExpression expression = qBoard.writer.contains(keyword);
                innerBuilder.or(expression);
            }
        });

        builder.and(innerBuilder);
        builder.and(qBoard.bno.gt(0L));

        Page<Board> result = repository.findAll(builder, pageable);

        return new BoardPageDTO(result);
    }


}

package org.zerock.board1.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.zerock.board1.domain.Board;
import org.zerock.board1.domain.QBoard;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsertDummies() {

        IntStream.range(1,300).forEach(i -> {
            Board board = Board.builder()
                    .title("title.."  + i)
                    .content("content..." + i)
                    .writer("user" + ( i % 10))
                    .build();

            System.out.println(boardRepository.save(board));
        });
    }

    @Test
    public void testQuerydsl1() {

        PageRequest pageRequest = PageRequest.of(0,10, Sort.Direction.DESC, "bno");

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = QBoard.board.title.contains("10");

        builder.and(expression);

        Page<Board> result = boardRepository.findAll(builder, pageRequest);

        System.out.println(result);

        result.get().forEach(board -> System.out.println(board));

    }

    @Test
    public void testSearch1(){
        String type = "tc";
        String keyword = "10";

        PageRequest pageRequest = PageRequest.of(0,10, Sort.Direction.DESC, "bno");

        String[] typeArr = type.split("");

        BooleanBuilder builder = new BooleanBuilder();

        QBoard qBoard = QBoard.board;

        BooleanBuilder innerBuilder = new BooleanBuilder();


        Arrays.stream(typeArr).forEach(word -> {
            System.out.println(word);

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

        System.out.println(innerBuilder.getValue());

        builder.and(innerBuilder);
        builder.and(qBoard.bno.gt(0L));

        Page<Board> result = boardRepository.findAll(builder, pageRequest);

        System.out.println(result);

        result.get().forEach(board -> System.out.println(board));


    }


}












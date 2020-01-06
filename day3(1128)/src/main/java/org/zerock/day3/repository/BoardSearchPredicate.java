package org.zerock.day3.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import org.zerock.day3.domain.QBoard;
import org.zerock.day3.dto.SearchDTO;

import lombok.extern.slf4j.Slf4j;


/**
 * BoardSearchPredicate
 */
@Slf4j
public class BoardSearchPredicate {

    public static Predicate searchSimple(SearchDTO dto){
       
        log.info(dto.toString());

        QBoard board = QBoard.board;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(dto.getType() == null){
            return booleanBuilder;
        }

        String type = dto.getType();
        String keyword = dto.getKeyword();

        if(type.equals("t")){
            booleanBuilder.and(board.title.contains(keyword));
        } else if(type.equals("d")){
             
        } else if(type.equals("c")){
            booleanBuilder.and(board.content.contains(keyword));
        }

        return booleanBuilder;
    }

}
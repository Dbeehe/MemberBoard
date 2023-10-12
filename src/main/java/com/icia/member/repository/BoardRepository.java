package com.icia.member.repository;

import com.icia.member.entity.BoardEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Modifying // insert, update, delete
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
//    @Query(value = "update board_table set board_hits=board_hits+1 where id=:id", nativeQuery = true)
    void increaseHits(@Param("id") Long id);


    Page<BoardEntity> findByBoardTitleContaining(String q, Pageable pageable);

    // 작성자으로 검색한 결과를 Page 객체로 리턴
    Page<BoardEntity> findByBoardWriterContaining(String q, Pageable pageable);









    Page<BoardEntity> findByBoardTitleContainingOrBoardWriterContaining(String q, String q1, PageRequest id);
}

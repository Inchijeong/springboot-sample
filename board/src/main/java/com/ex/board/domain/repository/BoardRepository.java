package com.ex.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.board.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

}

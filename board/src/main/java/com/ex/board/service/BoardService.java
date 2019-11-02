package com.ex.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ex.board.domain.entity.BoardEntity;
import com.ex.board.domain.repository.BoardRepository;
import com.ex.board.dto.BoardDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	@Transactional
	public List<BoardDto> getBoardlist() {
		List<BoardEntity> boardEntities = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for (BoardEntity boardEntity : boardEntities) {
			BoardDto boardDTO = BoardDto.builder()
					.id(boardEntity.getId())
					.title(boardEntity.getTitle())
					.content(boardEntity.getContent())
					.writer(boardEntity.getWriter())
					.createdDate(boardEntity.getCreateDate())
					.build();
			
			boardDtoList.add(boardDTO);
		}
		
		return boardDtoList; 
	};
	
	
	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}
	
	@Transactional
	public BoardDto getPost(Long id) {
		Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
		BoardEntity boardEntity = boardEntityWrapper.get();
		
		BoardDto boardDTO = BoardDto.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContent())
				.writer(boardEntity.getWriter())
				.createdDate(boardEntity.getCreateDate())
				.build();
		
		return boardDTO;
	}
	
	@Transactional
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
	}
	
}

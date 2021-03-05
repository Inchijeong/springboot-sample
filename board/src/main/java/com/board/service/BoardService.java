package com.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.board.domain.Board;
import com.board.dto.BoardDTO;
import com.board.repository.BoardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	public List<BoardDTO> getBoardlist() {
		List<Board> boardEntities = boardRepository.findAll();
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		for (Board Board : boardEntities) {
			BoardDTO boardDTO = BoardDTO.builder()
					.id(Board.getId())
					.title(Board.getTitle())
					.content(Board.getContent())
					.writer(Board.getWriter())
					.createdDate(Board.getCreateDate())
					.build();
			
			boardDTOList.add(boardDTO);
		}
		
		return boardDTOList; 
	};
	
	public Long savePost(BoardDTO boardDTO) {
		return boardRepository.save(boardDTO.toEntity()).getId();
	}
	
	public BoardDTO getPost(Long id) {
		Optional<Board> BoardWrapper = boardRepository.findById(id);
		Board Board = BoardWrapper.get();
		
		BoardDTO boardDTO = BoardDTO.builder()
				.id(Board.getId())
				.title(Board.getTitle())
				.content(Board.getContent())
				.writer(Board.getWriter())
				.createdDate(Board.getCreateDate())
				.build();
		
		return boardDTO;
	}
	
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
	}
	
}

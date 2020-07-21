package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{
	
	public List<Board> findBoardByTitle(String title);
	public Collection<Board> findByWriter(String writer);
	public Collection<Board> findByWriterLike(String writer);
	// 작성자에 대한 like % 키워드 %
	public Collection<Board> findByWriterContaining(String writer);
	// 작성자에 대한 like 키워드 %
	public Collection<Board> findByWriterStartingWith(String writer);
	// 작성자에 대한 like % 키워드
	public Collection<Board> findByWriterEndingWith(String writer);
	// OR 조건의 처리
	public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);	
	// title LIKE % ? % AND BNO > ?
	public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
	// title LIKE % ? % AND BNO < ?
	public Collection<Board> findByTitleContainingAndBnoLessThan(String keyword, Long num);
	// bno > ? ORDER BY bno DESC
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	// bno > ? ORDER BY bno DESC limit ?, ?
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	// Spring MVC와 연동할때 편리함
	public Page<Board> findByBnoLessThan(Long bno, Pageable paging);
}

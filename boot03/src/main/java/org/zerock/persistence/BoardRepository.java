package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
	
	// JPQL
	
	// 쿼리 안에서 테이블명을 엔티티로 사용	
	@Query("SELECT b FROM Board b WHERE b.title LiKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByTitle(String title);
	@Query("SELECT b FROM Board b WHERE b.content LiKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByContent(@Param("content") String content);
	// #{#entityName}는 <엔티티 타입, PK타입>에서 엔티티 타입을 자동으로 참고
	@Query("SELECT b FROM #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findEntityByWriter(String writer);
	// 필요한 컬럼만 가져올때 사용(리턴타입이 Object[])
	@Query("SELECT b.bno, b.title, b.writer, b.regdate FROM Board b WHERE b.title LiKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Object[]> findByTitle2(String title);
	// 복잡한 쿼리에 사용, 데이이 터베이스에 종속적, 남용하지 않는다.
	@Query(value = "SELECT b.bno, b.title, b.writer, b.regdate FROM tbl_boards b WHERE b.title LiKE %?1% AND b.bno > 0 ORDER BY b.bno DESC",
		   nativeQuery = true)
	public List<Object[]> findByTitle3(String title);
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByPage(Pageable pageable);
}

package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.QWebBoard;
import org.zerock.domain.WebBoard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard>{
	
	public default Predicate makePredicate(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QWebBoard board = QWebBoard.webBoard;
		
		// type if ~ else
		
		// bno > 0
		builder.and(board.bno.gt(0));
		
		if(type == null) {
			return builder;
		}
		
		switch(type) {
		case "t":
			builder.and(board.title.like("%" + keyword + "%"));
			break;
		case "c":
			builder.and(board.content.like("%" + keyword + "%"));
			break;
		case "w":
			builder.and(board.writer.like("%" + keyword + "%"));
			break;
		}
		
		return builder;
	}
	
	// @Query를 사용할 경우 문제
	// 조건이 생성될때 마다 메소드를 하나씩 추가해야함
	@Query("SELECT b.bno, b.title, b.writer, b.regdate, count(r) FROM WebBoard b "
			+ "LEFT OUTER JOIN b.replies r WHERE b.bno > 0 GROUP BY b")
	public List<Object[]> getListWithAll(Pageable page);
	
	@Query("SELECT b.bno, b.title, b.writer, b.regdate, count(r) FROM WebBoard b "
			+ "LEFT OUTER JOIN b.replies r WHERE b.title like %?1% AND b.bno > 0 GROUP BY b")
	public List<Object[]> getListWithTitle(String keyword, Pageable page);
	
	@Query("SELECT b.bno, b.title, b.writer, b.regdate, count(r) FROM WebBoard b "
			+ "LEFT OUTER JOIN b.replies r WHERE b.content like %?1% AND b.bno > 0 GROUP BY b")
	public List<Object[]> getListWithContent(String keyword, Pageable page);
	
	@Query("SELECT b.bno, b.title, b.writer, b.regdate, count(r) FROM WebBoard b "
			+ "LEFT OUTER JOIN b.replies r WHERE b.writer like %?1% AND b.bno > 0 GROUP BY b")
	public List<Object[]> getListWithWriter(String keyword, Pageable page);
}

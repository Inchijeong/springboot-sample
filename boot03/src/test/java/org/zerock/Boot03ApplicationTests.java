package org.zerock;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class Boot03ApplicationTests {

	@Autowired
	private BoardRepository repo;
	
	@Test
	public void testInsert200() {
		for(int i = 0; i <= 200; i++) {
			
			Board board = new Board();
			board.setTitle("제목.." + i);
			board.setContent("내용 ...." + i + " 채우기");
			board.setWriter("user0"+(i % 10));
			repo.save(board);
		}
	}
	
	@Test
	public void testByTitleOld() {
		
		// Before Java 8
		List<Board> list = repo.findBoardByTitle("제목..117");
		
		for(int i = 0, len = list.size(); i < len; i++) {
			System.out.println(list.get(i));
		}		
	}
	
	@Test
	public void testByTitleNew() {
		
		repo.findBoardByTitle("제목..117")
			.forEach(board -> System.out.println(board));
	}

	@Test
	public void testByWriter() {
		
		Collection<Board> results = repo.findByWriter("user00");
		
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByWriterContaining() {
		
		Collection<Board> results = repo.findByWriterContaining("05");
		
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByWriterEndingWith() {
		
		Collection<Board> results = repo.findByWriterEndingWith("05");
		
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitleContainingOrContentContaining() {
		
		Collection<Board> results = repo.findByTitleContainingOrContentContaining("5", "05");
		
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitleContainingAndBnoLessThan() {
		
		Collection<Board> results = repo.findByTitleContainingAndBnoLessThan("1", 50L);
		
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByBnoGreaterThanOrderByBnoDesc() {
		
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(50L);
		
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	void contextLoads() {
	}

}

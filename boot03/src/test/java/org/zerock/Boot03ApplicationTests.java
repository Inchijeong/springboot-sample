package org.zerock;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public void testBnoOrderByPaging() {
		
//		Pageable paging = new PageRequest(0, 10);
		Pageable paging = PageRequest.of(0, 10);
		
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
		
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testBnoGreaterPagingSort() {
		
//		Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
		
		Collection<Board> results = repo.findByBnoGreaterThan(0L, paging);
		
		results.forEach(board -> System.out.println(board));
	}

	@Test
	public void testBnoLessPagingSort() {
		
//		Pageable paging = new PageRequest(0, 20, Sort.Direction.ASC, "bno");
		Pageable paging = PageRequest.of(0, 20, Sort.Direction.ASC, "bno");
		
		Page<Board> result = repo.findByBnoLessThan(100L, paging);
		
		System.out.println("PAGE SIZE: " + result.getSize());
		System.out.println("TOTAL SIZE: " + result.getTotalPages());
		System.out.println("TOTAL COUNT: " + result.getTotalElements());
		System.out.println("NEXT: " + result.getPageable());
		
		
		List<Board> list = result.getContent();
		
		list.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitle() {
		
		repo.findByTitle("17").forEach(board -> System.out.println(board));
	}

	@Test
	public void testByContent() {
		
		repo.findByContent("17").forEach(board -> System.out.println(board));
	}

	@Test
	public void testEntityByContent() {
		
		repo.findEntityByWriter("17").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitle17() {
		
		repo.findByTitle2("17").forEach(arr -> System.out.println(Arrays.toString(arr)));
	}

	@Test
	public void testByTitle18() {
		
		repo.findByTitle2("18").forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
	
	@Test
	public void testByPaging() {
		
		Pageable pageable = PageRequest.of(0, 10);
		
		repo.findByPage(pageable).forEach(board -> System.out.println(board));
	}
	
	@Test
	void contextLoads() {
	}

}

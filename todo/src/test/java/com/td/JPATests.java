package com.td;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.td.domain.Todo;
import com.td.repository.TodoRepository;

@SpringBootTest
public class JPATests {

	@Autowired
	TodoRepository todoRepo;
	
	@Test
	public void insertTodo() {
		
//		IntStream.range(1, 10).forEach(i -> {
//			
//			Todo todo = new Todo();
//			todo.setTitle("Title " + i);
//			todo.setContent("Content... " + i);
//			
//			todoRepo.save(todo);
//		});
	}
}

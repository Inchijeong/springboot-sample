package com.td.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.td.domain.Todo;
import com.td.dto.TodoDto;
import com.td.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoService {

	private final TodoRepository todoRepository;
	
	public List<TodoDto> getTodoList() {		
		return todoRepository.findAll()
				.stream()
				.map(TodoDto::new)
				.collect(Collectors.toList());
	}
}

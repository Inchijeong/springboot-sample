package com.td.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.td.dto.TodoDTO;
import com.td.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoService {

	private final TodoRepository todoRepository;
	
	public List<TodoDTO> getTodoList() {
		return todoRepository.findAll()
				.stream()
				.map(TodoDTO::new)
				.collect(Collectors.toList());
	}
}

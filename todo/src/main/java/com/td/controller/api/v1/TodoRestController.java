package com.td.controller.api.v1;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.td.dto.TodoDto;
import com.td.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoRestController {

	private final TodoService todoService;
	
	@GetMapping("/list")
	public List<TodoDto> list(Model model) {
		return todoService.getTodoList();
	}
	
}

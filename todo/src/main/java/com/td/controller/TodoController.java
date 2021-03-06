package com.td.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.td.dto.TodoDto;
import com.td.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping("/list")
	public List<TodoDto> list(Model model) {
		return todoService.getTodoList();
	}
	
	@GetMapping("/detail")
	public void detail(Model model) {
		
	}
	
	@GetMapping("/write")
	public void write(Model model) {
		
	}
	
	@GetMapping("/edit")
	public void edit(Model model) {
		
	}
}

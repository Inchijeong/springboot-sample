package com.td.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.td.service.TodoService;

@RequestMapping("/todo")
@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping("/list")
	public void list(Model model) {
		
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

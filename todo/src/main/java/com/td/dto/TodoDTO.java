package com.td.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.td.domain.BaseTimeEntity;
import com.td.domain.Todo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class TodoDTO extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	public TodoDTO(Todo todo) {
		this.id = todo.getId();
		this.title = todo.getTitle();
		this.content = todo.getContent();
	}
	
	public Todo toEntity() {
		return Todo.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
	}
}

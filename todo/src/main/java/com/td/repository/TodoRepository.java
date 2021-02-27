package com.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.td.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

}

package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, String> {

	public Visitor findByIp(String ip);
	
}

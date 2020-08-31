package com.project.exercise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.exercise.model.Card;

public interface CardRepository extends JpaRepository <Card, Long>{
	Card findByCarddigits (String digits);
	
	Page<Card> findAll(Pageable page);
	
	
	

}
package com.project.exercise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.exercise.model.Card;

public interface CardRepository extends JpaRepository <Card, Long>{
	Optional<Card> findByCarddigits (String digits);
	

}
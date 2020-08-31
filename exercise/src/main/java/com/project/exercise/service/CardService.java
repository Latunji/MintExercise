package com.project.exercise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.project.exercise.model.Card;
import com.project.exercise.repository.CardRepository;

@Service
public class CardService{
	
	@Autowired
	private CardRepository repository;
	
	public Card saveCard(Card card) {
		System.out.println("card is: " +card);
		return repository.save(card);
	}
	
	public Card findByDigits(String digits){
		return repository.findByCarddigits(digits);
	}
	
	public Page<Card> findAllCard(String start, String limit){
		int st = Integer.valueOf(start);
		int lt = Integer.valueOf(limit);
		Pageable firstPageWithTwoElements = PageRequest.of(st, lt);
		Page<Card> allCardLimit = 
				  repository.findAll(firstPageWithTwoElements);
		return allCardLimit;
	}
	
//	public Optional<Card> findById(Long id){
//		return repository.findById(id);
//	}
//	
}

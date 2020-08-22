package com.project.exercise.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Optional<Card> findByDigits(String digits){
		return repository.findByCarddigits(digits);
	}
	
	
//	public Card findOne(int id){
//	 return repository.findOne(id);
//	}
}

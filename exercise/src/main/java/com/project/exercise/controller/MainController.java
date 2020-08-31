package com.project.exercise.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.exercise.model.Card;
import com.project.exercise.service.CardService;

@RestController
public class MainController {
	@Autowired
	private CardService cardservice;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String TOPIC = "com.ng.vela.even.card_verified";

	@GetMapping("/verify/{carddigits}")
	public String welcome(@PathVariable("carddigits") final String carddigits){
		 HttpURLConnection connection = null;
		 Card card = new Card();
         JSONObject response = new JSONObject();
         JSONObject newresponse = new JSONObject();
	        try {
	            URL url = new URL("https://lookup.binlist.net/"+carddigits);
	            connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            StringBuilder myResponse = new StringBuilder();
	            String my_response;
	            while ((my_response = rd.readLine()) != null) {
	                myResponse.append(my_response);
	            }
	            Card cd = cardservice.findByDigits(carddigits);
	            String res = myResponse.toString();
	            JSONObject rest = new JSONObject(res);
	            String scheme = rest.getString("scheme");
	            String type = rest.getString("type");
	            JSONObject newObJ = rest.getJSONObject("bank");
	            String bank = newObJ.getString("name");
	            
	            if (cd.getId() != null) {
	            	System.out.println("card exists....");
	            	cd.setCount(cd.getCount()+1);
	            	cardservice.saveCard(cd);
	            }
	            else {
	            	card.setBank(bank);
		            card.setScheme(scheme);
		            card.setCarddigits(carddigits);
		            card.setType(type);
	            System.out.println("new card....");
	            card.setCount(1L);
	            cardservice.saveCard(card);
	            }
	            response.put("scheme", scheme);
	            response.put("type", type);
	            response.put("bank", bank);
	            newresponse.put("success", "true");
	            newresponse.put("payload", response);
//	            kafkaTemplate.send(TOPIC, newresponse.toString());
	            return newresponse.toString();
	        } catch (IOException e) {
	            System.out.println(e.toString());
	            return null;
	        } finally {
	            if (connection != null) {
	                connection.disconnect();
	            }
	        }
	}
	
	@GetMapping("/stats/{start}/{limit}")
	public String cardscheme(@PathVariable("start") final String start,@PathVariable("limit") final String limit){
				System.out.println("got here");
	            Page<Card> C = cardservice.findAllCard(start, limit);
	           System.out.println("list print.... "+ C);
	            return "fgfgf";
	}
}

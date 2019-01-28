package com.smshub.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.smshub.pojos.SmsResponse;

@Controller
public class SendSms {
@RequestMapping(value="/sendSms")
	public String sendSms(@RequestParam("mobile") String mobile,@RequestParam("text") String text ) {
	
	String url=new String("https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=G6aewiBTq02ETclr5E7taA&senderid=SMSTST&channel=2&DCS=0&flashsms=0&number="+mobile+"&text="+text+"&route=1");
	
	
	StringBuffer uri=new StringBuffer("https://www.smsgatewayhub.com");
		uri.append("/api/mt/SendSMS?");
		uri.append("APIKey=").append("G6aewiBTq02ETclr5E7taA");
		uri.append("&senderid=").append("SMSTST");
		uri.append("&channel=").append("2");
		uri.append("&DCS=").append("0");
		uri.append("&flashsms=").append("0");
		uri.append("&number=").append(mobile);
		uri.append("&text=").append(text);
		uri.append("&route=").append("1");
		
		RestTemplate rt=new RestTemplate();
		
		HttpHeaders header=new HttpHeaders();
		HttpEntity<String> entity=new HttpEntity<String> ("",header);
		
		
		
		
		ResponseEntity<String> result = rt.exchange(url, HttpMethod.POST, entity, String.class);
		String response = result.getBody();
		System.out.println(response);
		Gson gson=new Gson();
		SmsResponse smsResponse = gson.fromJson(response, SmsResponse.class);
		System.out.println(smsResponse.getErrorMessage());
		
		
		
		
		
		
		return "SendSms";
		
	}
}
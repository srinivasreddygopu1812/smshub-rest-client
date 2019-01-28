package com.smshub.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.smshub.pojos.MessageData;
import com.smshub.pojos.SendSmsInJson;
import com.smshub.pojos.SmsResponse;
import com.smshub.pojos.UserAccount;
import com.smshub.pojos.UserMessages;

@Controller
public class SmsRequestUsingJson {
	@RequestMapping(value = "/sendSmsUsingjson")
	public String sendSms(@RequestParam("mobile") String mobile, @RequestParam("text") String text, Model model) {
System.out.println(mobile);
		String url = "https://www.smsgatewayhub.com/api/mt/SendSMS";

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);

		UserAccount userAccount = prepareAccount();

		List<UserMessages> userMessage = prepareUserMessage(mobile, text);

		SendSmsInJson smsJson = preparingSendSmsJson(userAccount, userMessage);

		Gson gson = new Gson();
		String jsondata = gson.toJson(smsJson);

		HttpEntity<String> entity = new HttpEntity<String>(jsondata, header);

		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> result = rt.exchange(url, HttpMethod.POST, entity, String.class);

		String response = result.getBody();
		System.out.println(response);

		SmsResponse smsResponse2 = gson.fromJson(response, SmsResponse.class);
		String[] mbl = mobile.split(",");
		ArrayList<String > mblList=new ArrayList<String>(Arrays.asList(mbl));
		
		List<MessageData> messageData = smsResponse2.getMessageData();
		for (MessageData messageData2 : messageData) {
	if(mblList.contains(messageData2.getNumber()))
		mblList.remove(messageData2.getNumber());
		}
		if(!mblList.isEmpty())
			model.addAttribute("mobilefails", mblList);

		if (smsResponse2.getErrorCode().equals("000")) {
			model.addAttribute("successMsg", "Message Sent Successfully");
			return "SmsUsingJson";
		}

		else {
			model.addAttribute("failureMsg", smsResponse2.getErrorMessage());
			return "SmsUsingJson";

		}

	}

	private SendSmsInJson preparingSendSmsJson(UserAccount userAccount, List<UserMessages> userMessage) {
		SendSmsInJson smsJson = new SendSmsInJson();
		smsJson.setAccount(userAccount);
		smsJson.setMessages(userMessage);
		return smsJson;
	}

	private List<UserMessages> prepareUserMessage(String mobile, String text) {

		String[] mobileNumbers = mobile.split(",");	
	List<String> mboileNumbersList=new ArrayList<String>(Arrays.asList(mobileNumbers));
	List<UserMessages> userMessage = new ArrayList<UserMessages>();
		for (String mblNumbers : mboileNumbersList) {
			UserMessages userMsg = new UserMessages();
			userMsg.setNumber(mblNumbers);
			userMsg.setText(text);

			userMessage.add(userMsg);
		}

		return userMessage;
	}

	private UserAccount prepareAccount() {
		UserAccount userAccount = new UserAccount();
		userAccount.setUser("srinivasreddygopu1812");
		userAccount.setPassword("640224");
		userAccount.setSenderId("SMSTST");
		userAccount.setChannel("1");
		userAccount.setDCS("0");
		userAccount.setSchedTime(null);
		userAccount.setGroupId(null);
		return userAccount;
	}

}

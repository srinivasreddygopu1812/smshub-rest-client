package com.smshub.pojos;

import java.util.List;

public class SendSmsInJson {
	private UserAccount Account;
	private List<UserMessages> Messages;
	public UserAccount getAccount() {
		return Account;
	}
	public void setAccount(UserAccount account) {
		Account = account;
	}
	public List<UserMessages> getMessages() {
		return Messages;
	}
	public void setMessages(List<UserMessages> messages) {
		Messages = messages;
	}
	
	
	
	

}

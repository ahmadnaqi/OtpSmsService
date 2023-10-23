package com.cfs.biz.dto;

import lombok.Data;

@Data
public class PasswordResetRequestDto {
	private String userName;
    private String oneTimePassword;    
	private String phoneNumber;//destination
    
	public String getUserName() {
		return userName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getOneTimePassword() {
		return oneTimePassword;
	}
	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}
	
	@Override
	public String toString() {
		return "PasswordResetRequestDto [userName=" + userName + ", oneTimePassword=" + oneTimePassword
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}

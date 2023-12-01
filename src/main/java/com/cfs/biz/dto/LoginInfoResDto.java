package com.cfs.biz.dto;

import lombok.Data;

@Data
public class LoginInfoResDto {
	private String userName;        
	private String phoneNumber;//destination
	private OtpStatus status;
	private String message;
	
	public LoginInfoResDto(OtpStatus status, String userName2) {
		// TODO Auto-generated constructor stub
		this.status=status;
		this.userName=userName2;
		
	}
	public LoginInfoResDto(OtpStatus status,String userName, String phoneNumber,String message) {
		// TODO Auto-generated constructor stub
		this.status=status;
		this.userName=userName;
		this.phoneNumber=phoneNumber;
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public OtpStatus  getStatus() {
		return status;
	}
	public void setStatus(OtpStatus  status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "LoginInfoResDto [userName=" + userName + ", phoneNumber=" + phoneNumber + ", status=" + status
				+ ", message=" + message + "]";
	}
	
		

}

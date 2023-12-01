package com.cfs.biz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="biz_user_login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    @Id
    @GeneratedValue
    private int id;    
	private String userName;
    private String userPassword;
    private String userEmail;
    private String mobileNumber;
    private String userActiveStatus;
    private Date userLastLogin;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserActiveStatus() {
		return userActiveStatus;
	}
	public void setUserActiveStatus(String userActiveStatus) {
		this.userActiveStatus = userActiveStatus;
	}
	public Date getUserLastLogin() {
		return userLastLogin;
	}
	public void setUserLastLogin(Date userLastLogin) {
		this.userLastLogin = userLastLogin;
	}
   
}



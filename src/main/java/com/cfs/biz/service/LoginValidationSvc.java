package com.cfs.biz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfs.biz.dto.LoginInfoResDto;
import com.cfs.biz.dto.OtpStatus;
import com.cfs.biz.entity.LoginInfo;
import com.cfs.biz.repository.LoginInfoRepository;

@Service
public class LoginValidationSvc {
	@Autowired
	LoginInfoRepository loginInfoRepository;
	@Autowired
	OTPService otpService;
	
	public LoginInfoResDto validatelogin(String UserName,String Password ){
		Optional<LoginInfo> UserNameList=loginInfoRepository.findByUserNameAndUserPassword(UserName, Password);
		
		LoginInfoResDto loginInfoResDto=null;
		
		if(UserNameList.isPresent()) {
			 String UserMobileNumber=UserNameList.get().getMobileNumber();
		     boolean flag=otpService.sendOTPForUser(UserMobileNumber, UserName);		 
			 
			 loginInfoResDto= new LoginInfoResDto(OtpStatus.SUCCESS,UserName);			 
		}else {			
			 loginInfoResDto= new LoginInfoResDto(OtpStatus.FAILED,UserName);	
		   
		}
		return loginInfoResDto;
		
	}
	
	public LoginInfoResDto validateUser(String UserName ){
      Optional<LoginInfo> UserNameList=loginInfoRepository.findByUserName(UserName);
      LoginInfoResDto loginInfoResDto=null;
		if(UserNameList.isPresent()) {
			 String UserMobileNumber=UserNameList.get().getMobileNumber();
		     boolean flag=otpService.sendOTPForUser(UserMobileNumber, UserName);			
			 if( flag) {
				 loginInfoResDto= new LoginInfoResDto(OtpStatus.SUCCESS,UserName,null, "OTP SENT"); 
			 }
		}else {
			 loginInfoResDto= new LoginInfoResDto(OtpStatus.FAILED,UserName,null, "INVALID USER NAME");
		}
		return loginInfoResDto;
	}
	
    public LoginInfoResDto validateloginUsingPhone(String UserPhoneNumber,String Password ){
    	
        Optional<LoginInfo> UserNameList=loginInfoRepository.findByMobileNumberAndUserPassword(UserPhoneNumber, Password);
		
		LoginInfoResDto loginInfoResDto=null;
		
		if(UserNameList.isPresent()) {
			 String UserName=UserNameList.get().getUserName();
		     boolean flag=otpService.sendOTPForUser(UserPhoneNumber, UserName);	 
			 
			 loginInfoResDto= new LoginInfoResDto(OtpStatus.SUCCESS,UserName);			 
		}else {			
			 loginInfoResDto= new LoginInfoResDto(OtpStatus.FAILED,UserPhoneNumber);	
		   
		}
		return loginInfoResDto;
		
		
		
	}
    

}

package com.cfs.biz.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfs.biz.dto.LoginInfoResDto;
import com.cfs.biz.dto.OtpStatus;
import com.cfs.biz.entity.LoginInfo;
import com.cfs.biz.repository.LoginInfoRepository;

@Service
public class LoginValidationSvc {
	private final static Logger logger = LoggerFactory.getLogger(LoginValidationSvc.class);
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
			 if(flag) {
				logger.info("valid login and OTP sent on user mobile");
			   loginInfoResDto= new LoginInfoResDto(OtpStatus.SUCCESS,UserName);
			 }else {	
				 logger.info("valid login but could not send OTP on user mobile");
			   loginInfoResDto= new LoginInfoResDto(OtpStatus.FAILED,UserName,null, "Could not send OTP,Try after sometime!");
			 }
		}else {
			logger.info("Invalid login,Wrong credentails");
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
				 logger.info("Valid user and otp sent on user mobile");
				 loginInfoResDto= new LoginInfoResDto(OtpStatus.SUCCESS,UserName,null, "OTP SENT"); 
			 }else {
				 logger.info("Valid user but could not send OTP on user mobile");
				 loginInfoResDto= new LoginInfoResDto(OtpStatus.FAILED,UserName,null, "Could not send OTP,Try after sometime!"); 
			 }
		}else {
			logger.info("Invalid user  so OTP not possible.");
			 loginInfoResDto= new LoginInfoResDto(OtpStatus.FAILED,UserName,null, "INVALID USER NAME");
		}
		return loginInfoResDto;
	}
	
    public LoginInfoResDto validateloginUsingPhone(String UserPhoneNumber,String Password ){    	
        Optional<LoginInfo> UserNameList=loginInfoRepository.findByMobileNumberAndUserPassword(UserPhoneNumber, Password);		
		LoginInfoResDto loginInfoResDto=null;
		
		if(UserNameList.isPresent()) {
			 String UserName=UserNameList.get().getUserName();
		     boolean flag = otpService.sendOTPForUser(UserPhoneNumber, UserName);	 
		     if(flag) {
				 logger.info("valid login and otp sent on user mobile"); 
			     loginInfoResDto= new LoginInfoResDto(OtpStatus.SUCCESS,UserName);
		     }else {
		    	 logger.info("valid login could not sent OTP on user mobile");
				 loginInfoResDto= new LoginInfoResDto(OtpStatus.FAILED,UserName,null, "Could not send OTP,Try after sometime!");
		     }
		}else {	
			 logger.info("Invalid user/mobile number .......");
			 loginInfoResDto= new LoginInfoResDto(OtpStatus.FAILED,UserPhoneNumber);	
		   
		}
		return loginInfoResDto;
		
		
		
	}
    

}

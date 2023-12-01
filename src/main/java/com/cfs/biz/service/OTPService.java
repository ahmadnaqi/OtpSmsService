package com.cfs.biz.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfs.biz.config.TwilioConfig;
import com.cfs.biz.controller.RouterController;
import com.cfs.biz.dto.LoginInfoResDto;
import com.cfs.biz.dto.OtpStatus;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.text.DecimalFormat;
import java.util.Random;

@Service
public class OTPService {
	private final static Logger logger = LoggerFactory.getLogger(OTPService.class);
	
	   @Autowired
	    private TwilioConfig twilioConfig;
	    Map<String, String> otpMap = new HashMap<>();


	    
     public boolean sendOTPForUser(String UserMobileNumber, String UserName) {
    	   boolean flag=false;
    	    logger.info("UserMobileNumber:"+UserMobileNumber);
	    	logger.info("UserName:"+UserName);
	       
	        try {
	            PhoneNumber to = new PhoneNumber(UserMobileNumber);
	            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
	            logger.info("to Phonenumber:"+to);
	            logger.info("from Phonenumber:"+from);
	            String otp = generateOTP();
	            logger.info("OTP:"+otp);
	            String otpMessage = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
	            Message message = Message.creator(to, from,otpMessage).create();
	            logger.info("OTP MessageID:"+message.getSid());
	            otpMap.put(UserName, otp);
	            //passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);
	            flag=true;
	        } catch (Exception ex) {
	            //passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.FAILED, ex.getMessage());
	            flag=false;
	        }
	        return flag;
	    }

	    public LoginInfoResDto validateOTP(String userInputOtp, String userName) {
	    	logger.info("userInputOtp:"+userInputOtp);
            logger.info("userName:"+userName);
            LoginInfoResDto loginInfoResDto=null; 
	    	
	        if (userInputOtp.equals(otpMap.get(userName))) {
	            otpMap.remove(userName,userInputOtp);	            
	            loginInfoResDto=  new LoginInfoResDto(OtpStatus.SUCCESS,userName, null, "Valid OTP please proceed with your transaction !"); 
	        } else {
	        	loginInfoResDto=  new LoginInfoResDto(OtpStatus.FAILED,userName, null, "Invalid OTP please retry !");
	            
	        }
	        
	        return loginInfoResDto;
	    }

	    //6 digit otp
	    private String generateOTP() {
	        return new DecimalFormat("000000").format(new Random().nextInt(999999));
	    }

	}



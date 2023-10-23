package com.cfs.biz.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfs.biz.config.TwilioConfig;
import com.cfs.biz.dto.OtpStatus;
import com.cfs.biz.dto.PasswordResetRequestDto;
import com.cfs.biz.dto.PasswordResetResponseDto;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.text.DecimalFormat;
import java.util.Random;

@Service
public class OTPService {
	
	   @Autowired
	    private TwilioConfig twilioConfig;
	    Map<String, String> otpMap = new HashMap<>();

	    public PasswordResetResponseDto sendOTPForPasswordReset(PasswordResetRequestDto passwordResetRequestDto) {
	    	
	    	System.out.println("passwordResetRequestDto:"+passwordResetRequestDto);

	        PasswordResetResponseDto passwordResetResponseDto = null;
	        try {
	            PhoneNumber to = new PhoneNumber(passwordResetRequestDto.getPhoneNumber());
	            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
	            System.out.println("to Phonenumber:"+to);
	            System.out.println("from Phonenumber:"+from);
	            String otp = generateOTP();
	            System.out.println("otp:"+otp);
	            String otpMessage = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
	            Message message = Message.creator(to, from,otpMessage).create();
	            System.out.println(message.getSid());
	            otpMap.put(passwordResetRequestDto.getUserName(), otp);
	            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);
	        } catch (Exception ex) {
	            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.FAILED, ex.getMessage());
	        }
	        return passwordResetResponseDto;
	    }

	    public String validateOTP(String userInputOtp, String userName) {
	    	System.out.println("userInputOtp:"+userInputOtp);
            System.out.println("userName:"+userName);
	    	
	        if (userInputOtp.equals(otpMap.get(userName))) {
	            otpMap.remove(userName,userInputOtp);
	            return new String("Valid OTP please proceed with your transaction !");
	        } else {
	            return new String(("Invalid otp please retry !"));
	        }
	    }

	    //6 digit otp
	    private String generateOTP() {
	        return new DecimalFormat("000000").format(new Random().nextInt(999999));
	    }

	}



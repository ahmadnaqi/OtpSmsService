package com.cfs.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cfs.biz.dto.PasswordResetRequestDto;
import com.cfs.biz.dto.PasswordResetResponseDto;
import com.cfs.biz.service.OTPService;

@RestController
public class RouterController {
	@Autowired
    private OTPService otpService;

    
	@GetMapping("/router/ping")
	public String showStatus() {
		System.out.println("Request recieved on server");
		return "Service is up and running!!!!!";
	}
	
    
    @PostMapping("/router/sendOTP")
    public @ResponseBody PasswordResetResponseDto sendOTP(@RequestBody PasswordResetRequestDto obj) {
    	System.out.println("Received request:"+obj);
    	return otpService.sendOTPForPasswordReset(obj);
        
    }
    
    @PostMapping("/router/validateOTP")
    public String validateOTP(@RequestBody PasswordResetRequestDto obj) {
       System.out.println("Received request:"+obj);
       return otpService.validateOTP( obj.getOneTimePassword(),obj.getUserName());
        
    }
}



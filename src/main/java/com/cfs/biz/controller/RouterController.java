package com.cfs.biz.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cfs.biz.dto.LoginInfoReqDto;
import com.cfs.biz.dto.LoginInfoResDto;
import com.cfs.biz.dto.OtpStatus;
import com.cfs.biz.service.LoginValidationSvc;
import com.cfs.biz.service.OTPService;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.LoggerFactory;
@Slf4j
@RestController
public class RouterController {
	private final static Logger logger = LoggerFactory.getLogger(RouterController.class);
	@Autowired
    private OTPService otpService;	
	@Autowired
    private LoginValidationSvc loginValidationSvc;;

    
	@GetMapping("/router/ping")
	public String showStatus() {
		System.out.println("Request recieved on server");
		return "Service is up and running!!!!!";
	}
	
	@PostMapping("/router/login")
    public ResponseEntity<?>  validateLogin(@RequestBody LoginInfoReqDto obj) {
    	logger.info("Received request for validateLogin :"+obj);
    	String UserPhoneNumber=obj.getPhoneNumber();
    	String LoginUserName=obj.getUserName();
    	String LoginPassword=obj.getUserPassword();
    	logger.info("UserPhoneNumber:"+UserPhoneNumber);
    	logger.info("LoginUserName:"+LoginUserName);
    	logger.info("LoginPassword:"+LoginPassword);
    	LoginInfoResDto loginInfoResDto=null;
    	
    	if((UserPhoneNumber == null || UserPhoneNumber.isEmpty() ) && ( LoginUserName == null ||LoginUserName.isEmpty() )){
    		logger.info("Invalid value found in request object");
    		return new ResponseEntity<LoginInfoResDto>(new LoginInfoResDto(OtpStatus.FAILED,LoginUserName,UserPhoneNumber,"INVALID REQUEST OBJECT"),HttpStatus.BAD_REQUEST);
    	}
    	
    	if(UserPhoneNumber ==null || UserPhoneNumber.isEmpty()) {
    	     loginInfoResDto=loginValidationSvc.validatelogin(LoginUserName, LoginPassword);
    		logger.info("EXIT:Response sent:"+loginInfoResDto);
    		return new ResponseEntity<LoginInfoResDto>(loginInfoResDto,HttpStatus.OK);
    		
    	}else if(LoginUserName ==null ||LoginUserName.isEmpty() ) {
    	    loginInfoResDto=loginValidationSvc.validateloginUsingPhone(UserPhoneNumber, LoginPassword);
    		logger.info("EXIT:Response sent:"+loginInfoResDto);
    		return new ResponseEntity<LoginInfoResDto>(loginInfoResDto,HttpStatus.OK);
    	} 
    	
    	loginInfoResDto=new LoginInfoResDto(OtpStatus.FAILED,LoginUserName,UserPhoneNumber,"INVALID REQUEST OBJECT");
    	logger.info("EXIT:Response sent:"+loginInfoResDto);
    	return new ResponseEntity<LoginInfoResDto>(loginInfoResDto,HttpStatus.BAD_REQUEST);
    }
	
    
    @PostMapping("/router/sendOTP")
    public ResponseEntity<LoginInfoResDto> sendOTP(@RequestBody LoginInfoReqDto obj) {    	
    	logger.info("ENTRY:Received request for sendOTP:"+obj);    	
    	String LoginUserName= obj.getUserName();
    	logger.info("LoginUserName:"+LoginUserName);    	
    	LoginInfoResDto loginInfoResDto=null;
    	
    	if(LoginUserName == null || LoginUserName.isEmpty()) {    		  
    		 loginInfoResDto=new LoginInfoResDto(OtpStatus.FAILED,LoginUserName,null,"Either UserName is empty/null");
    	} else {
    		 loginInfoResDto=loginValidationSvc.validateUser(LoginUserName);
    	}
    	logger.info("EXIT:Response sent:"+loginInfoResDto);    	
    	return new ResponseEntity<LoginInfoResDto>(loginInfoResDto,HttpStatus.OK);
    	
    	
        
    }
    
    @PostMapping("/router/validateOTP")
    public ResponseEntity<LoginInfoResDto> validateOTP(@RequestBody LoginInfoReqDto obj) {
       logger.info("ENTRY Received request for validateOTP:"+obj);
       String OneTimePassword=obj.getOneTimePassword();
       String LoginUserName= obj.getUserName();
       logger.info("OneTimePassword:"+OneTimePassword);
       logger.info("LoginUserName:"+LoginUserName);
       LoginInfoResDto loginInfoResDto=null;
       if(LoginUserName == null || LoginUserName.isEmpty()) {    		  
  		 loginInfoResDto=new LoginInfoResDto(OtpStatus.FAILED,LoginUserName,null,"Either UserName is empty/null");
  	   } 
       if(OneTimePassword == null || OneTimePassword.isEmpty()) {    		  
  		 loginInfoResDto=new LoginInfoResDto(OtpStatus.FAILED,LoginUserName,null,"Either entered OTP is empty/null");
  	   }        
      
       loginInfoResDto= otpService.validateOTP( OneTimePassword,LoginUserName);
       logger.info("EXIT:Response sent:"+loginInfoResDto);  
       return new ResponseEntity<LoginInfoResDto>(loginInfoResDto,HttpStatus.OK);
        
    }
}



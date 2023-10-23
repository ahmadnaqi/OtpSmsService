package com.cfs.biz;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cfs.biz.config.TwilioConfig;
import com.twilio.Twilio;

@SpringBootApplication
public class OtpSmsServiceApplication {
	
	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void initTwilio(){
		System.out.println("<------inside initTwilio() method--->");
		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
	}


	public static void main(String[] args) {
		SpringApplication.run(OtpSmsServiceApplication.class, args);
	}

}

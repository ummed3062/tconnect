package com.tconnect.util;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;


@Component
public class Utilities {


	public static String generateOTP() {
		StringBuilder otp = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();
		for (int i = 0; i < 6; i++) {
			otp.append(secureRandom.nextInt(10));
		}
		return otp.toString();
	}
}


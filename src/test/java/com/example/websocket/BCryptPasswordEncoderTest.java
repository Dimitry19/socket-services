package com.example.websocket;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		String encodedPWD = bpe.encode("ADMIN");
		System.out.println(encodedPWD);
	}
}

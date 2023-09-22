package in.fssa.minimal.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomValue {
	public static long generateRandomAadharNumber() {
	        String nNum = "0123456789";
	        String fNum = "9876";
	        String num = "";
	        for (int i = 0; i < 12; i++) {
	            if (i == 0) {
	                num += fNum.charAt((int) (Math.random() * fNum.length()));
	            } else {
	                num += nNum.charAt((int) (Math.random() * nNum.length()));
	            }
	        }
	        long numericValue = Long.parseLong(num);
	        return numericValue;
	   
	}


	public static String generateRandomString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			randomString.append(characters.charAt(index));
		}

		return randomString.toString();
	}

	
}

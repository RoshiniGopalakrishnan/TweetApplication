package com.tweetapp.util;

import java.util.regex.Matcher;

import com.tweetapp.constants.Constants;

public class EmailUtil {
	public static boolean emailValidate(String emailStr) {
		Matcher matcher = Constants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
}
package com.tweetapp.constants;

import java.util.regex.Pattern;

public class Constants {

	public static final String DOB_FORMAT = "yyyy/MM/dd";
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	
	public static final String GET_ALL_TWEETS = "SELECT tweet_msg FROM tweet ";
	public static final String GET_USER_TWEETS = "SELECT tweet_msg FROM tweet WHERE user_email_id = ? ";
	public static final String SAVE_TWEET = "INSERT INTO tweet(tweet_msg,user_email_id)values(?,?)";

	public static final String GET_ALL_USERS = "SELECT us_first_name FROM user ";
	public static final String SAVE_USER = "INSERT INTO user (`us_first_name`, `us_last_name`, `us_gender`, `us_date_of_birth`, `us_email_id`, `us_password`, `us_isvalid`)  VALUES (?,?,?,?,?,?,?)";
	public static final String GET_USER = "SELECT us_email_id, us_password from user WHERE us_email_id = ?";
	public static final String GET_STATUS = "SELECT us_isvalid from user WHERE us_email_id = ?";
	public static final String SET_STATUS = "UPDATE user SET us_isvalid = ? WHERE us_email_id = ?";

	public static final String RESET_PASSWORD = "UPDATE user SET us_password = ? WHERE us_email_id = ?";
}
package com.tweetapp.service;

import java.util.List;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.model.Tweet;

public class TweetService {

	TweetDao repo = new TweetDao();

	public void saveTweet(String message, String username) throws Exception {
		repo.savePost(message, username);
	}

	public List<Tweet> getUsertweets(String username) throws Exception {
		return repo.getPostsByUser(username);
	}

	public List<Tweet> getAlltweets() {
		return repo.getPosts();
	}

}
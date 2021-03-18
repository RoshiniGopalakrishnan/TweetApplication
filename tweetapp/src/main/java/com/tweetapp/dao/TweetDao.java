package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tweetapp.constants.Constants;
import com.tweetapp.model.Tweet;
import com.tweetapp.util.DbHandler;

public class TweetDao {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public List<Tweet> getPosts() {
		connection = DbHandler.getConnection();
		List<Tweet> postList = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(Constants.GET_ALL_TWEETS);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				do {
					String message = resultSet.getString("tweet_msg");
					Tweet post = new Tweet();
					post.setTweet(message);
					postList.add(post);
				} while (resultSet.next());
			} else {
				System.err.println("No posts found");
			}
			return postList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closedb();
			} catch (SQLException sqlException) {
				throw new RuntimeException("Connection is not closed properly");
			}
		}
		return postList;
	}

	public List<Tweet> getPostsByUser(String username) throws Exception {
		connection = DbHandler.getConnection();
		List<Tweet> posts = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(Constants.GET_USER_TWEETS);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				do {
					String message = resultSet.getString("tweet_msg");
					Tweet post = new Tweet();
					post.setTweet(message);
					posts.add(post);
				} while (resultSet.next());
			} else {
				throw new Exception();
			}
			return posts;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closedb();
			} catch (SQLException sqlException) {
				throw new RuntimeException("Connection is not closed properly");
			}
		}
		return posts;
	}

	public void savePost(String tweetMessage, String username) throws Exception {
		connection = DbHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(Constants.SAVE_TWEET);
			preparedStatement.setString(1, tweetMessage);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			try {
				closedb();
			} catch (SQLException sqlException) {
				throw new RuntimeException("Connection is not closed properly");
			}
		}

	}

	public void closedb() throws SQLException {

		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
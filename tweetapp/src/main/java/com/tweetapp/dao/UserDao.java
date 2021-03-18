package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tweetapp.constants.Constants;
import com.tweetapp.model.User;
import com.tweetapp.util.DbHandler;

public class UserDao {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public User getUser(String username) throws Exception {
		connection = DbHandler.getConnection();
		User user = new User();
		try {
			preparedStatement = connection.prepareStatement(Constants.GET_USER);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String email = resultSet.getString("us_email_id");
				String password = resultSet.getString("us_password");
				user.setEmailId(email);
				user.setPassword(password);
			} else {
				throw new Exception("Invalid User, kindly register to login");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closedb();
			} catch (SQLException sqlException) {
				throw new RuntimeException("Connection is not closed properly");
			}
		}
		return user;
	}

	public void saveUser(User user) throws Exception {
		connection = DbHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(Constants.SAVE_USER);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getGender());
			preparedStatement.setDate(4, new Date(user.getDateOfBirth().getTime()));
			preparedStatement.setString(5, user.getEmailId());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setBoolean(7, false);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closedb();
			} catch (SQLException sqlException) {
				throw new RuntimeException("Connection is not closed properly");
			}
		}
	}

	public void resetPassword(String emailId, String password) throws Exception {
		connection = DbHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(Constants.RESET_PASSWORD);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, emailId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closedb();
			} catch (SQLException sqlException) {
				throw new RuntimeException("Connection is not closed properly");
			}
		}
	}
	
	public void setStatus(Boolean isValid, String emailId) throws Exception {
		connection = DbHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(Constants.SET_STATUS);
			preparedStatement.setBoolean(1, isValid);
			preparedStatement.setString(2, emailId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closedb();
			} catch (SQLException sqlException) {
				throw new RuntimeException("Connection is not closed properly");
			}
		}
	}
	
//	public Boolean getStatus(String username) throws Exception {
//		connection = DbHandler.getConnection();
//		User user = new User();
//		try {
//			preparedStatement = connection.prepareStatement(BatchConstants.GET_USER);
//			preparedStatement.setString(1, username);
//			resultSet = preparedStatement.executeQuery();
//			if (resultSet.next()) {
//				String email = resultSet.getString("us_email");
//				String password = resultSet.getString("us_password");
//				user.setEmail(email);
//				user.setPassword(password);
//			} else {
//				throw new Exception("Invalid User, kindly register to login");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				closedb();
//			} catch (SQLException sqlException) {
//				throw new RuntimeException("Connection is not closed properly");
//			}
//		}
//		return user;
//	}

	public List<String> getAllUsers() {
		connection = DbHandler.getConnection();
		List<String> names = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(Constants.GET_ALL_USERS);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				do {
					String firstname = resultSet.getString("us_first_name");
					names.add(firstname);
				} while (resultSet.next());
			} else {
				System.err.println("There are no users, Register to add users");
			}
			return names;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closedb();
			} catch (SQLException sqlException) {
				throw new RuntimeException("Connection is not closed properly");
			}
		}
		return names;
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
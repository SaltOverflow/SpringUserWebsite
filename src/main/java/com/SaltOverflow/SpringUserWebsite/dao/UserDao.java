package com.SaltOverflow.SpringUserWebsite.dao;

import java.util.List;

import com.SaltOverflow.SpringUserWebsite.model.User;

public interface UserDao {
	List<User> getUsers();
	User getUser(Integer id);
	void saveUser(User user);
	void updateUser(User user);
	boolean deleteUser(Integer id);
}

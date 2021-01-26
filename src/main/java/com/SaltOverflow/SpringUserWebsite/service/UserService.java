package com.SaltOverflow.SpringUserWebsite.service;

import java.util.List;

import com.SaltOverflow.SpringUserWebsite.model.User;

public interface UserService {
	List<User> getAllUsers();
	User getUser(Integer id);
	void saveOrUpdateUser(User user);
	boolean deleteUser(Integer id);
}

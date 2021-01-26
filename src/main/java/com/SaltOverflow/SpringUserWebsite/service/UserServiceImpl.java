package com.SaltOverflow.SpringUserWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SaltOverflow.SpringUserWebsite.dao.UserDao;
import com.SaltOverflow.SpringUserWebsite.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getAllUsers() {
		return userDao.getUsers();
	}

	@Override
	public User getUser(Integer id) {
		return userDao.getUser(id);
	}

	@Override
	public void saveOrUpdateUser(User user) {
		if (getUser(user.getId()) == null) {
			userDao.saveUser(user);
		} else {
			userDao.updateUser(user);
		}
	}

	@Override
	public boolean deleteUser(Integer id) {
		return userDao.deleteUser(id);
	}

}

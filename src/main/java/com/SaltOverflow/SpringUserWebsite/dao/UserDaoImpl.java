package com.SaltOverflow.SpringUserWebsite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.SaltOverflow.SpringUserWebsite.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	NamedParameterJdbcTemplate sqlQueryer;
	
	@Override
	public List<User> getUsers() {
		String sql = "SELECT * FROM users";
		
		List<User> result = sqlQueryer.query(sql, new DataToUser());
		return result;
	}

	@Override
	public User getUser(Integer id) {
		String sql = "SELECT * FROM users WHERE id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		User result = null;
		try {
			result = sqlQueryer.queryForObject(sql, params, new DataToUser());
		} catch (EmptyResultDataAccessException e) { }
		return result;
	}
	
	private static final class DataToUser implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			return user;
		}
	}

	@Override
	public void saveUser(User user) {
		String sql = "INSERT INTO users (firstName,lastName) values (:firstName,:lastName)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		sqlQueryer.update(sql, userToParams(user), keyHolder);
		user.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE users SET firstName=:firstName, lastName=:lastName WHERE id=:id";
		
		sqlQueryer.update(sql, userToParams(user));		
	}
	
	private SqlParameterSource userToParams(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", user.getId());
		params.addValue("firstName", user.getFirstName());
		params.addValue("lastName", user.getLastName());
		return params;
	}

	@Override
	public boolean deleteUser(Integer id) {
		String sql = "DELETE FROM users WHERE id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		return sqlQueryer.update(sql, params) != 0;
	}
	
	
}

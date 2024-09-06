package com.bptn.feedAppAutomation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bptn.feedAppAutomation.bean.User;
import com.bptn.feedAppAutomation.dao.mapper.UserMapper;

@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public User findByUsername(String username) {
		
        String sql = "SELECT * FROM \"User\" WHERE username = ?";
		
		return this.jdbcTemplate.queryForObject(sql, new UserMapper(), username);
	}

}
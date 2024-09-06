package com.bptn.feedAppAutomation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bptn.feedAppAutomation.bean.FeedMetaData;
import com.bptn.feedAppAutomation.bean.User;
import com.bptn.feedAppAutomation.dao.mapper.FeedMetaDataMapper;

@Repository
public class FeedMetaDataDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public FeedMetaData getLatestFeedMetaDataByUserForFeedId(User user, int feedId) {

		String sql = "SELECT * FROM \"FeedMetaData\" WHERE \"actionUserId\" = ? AND \"feedId\" = ? ORDER BY \"feedMetaDataId\" DESC LIMIT 1";

	    return this.jdbcTemplate.queryForObject(sql, new FeedMetaDataMapper(), user.getUserId(), feedId);
	}

}
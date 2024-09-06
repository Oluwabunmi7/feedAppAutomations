package com.bptn.feedAppAutomation.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bptn.feedAppAutomation.bean.Feed;




public class FeedMapper implements RowMapper<Feed> {

	@Override
	public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Feed feed = new Feed();
		
		feed.setFeedId(rs.getInt("feedId"));
		feed.setPicture(rs.getString("picture"));
		feed.setContent(rs.getString("content"));
		feed.setCreatedOn(rs.getTimestamp("createdOn"));
				
		return feed;
	}

}
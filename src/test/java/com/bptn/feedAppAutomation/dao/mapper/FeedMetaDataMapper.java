package com.bptn.feedAppAutomation.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bptn.feedAppAutomation.bean.FeedMetaData;

public class FeedMetaDataMapper implements RowMapper<FeedMetaData> {

	@Override
	public FeedMetaData mapRow(ResultSet rs, int rowNum) throws SQLException {
		FeedMetaData feedMetaData = new FeedMetaData();

		feedMetaData.setFeedMetaDataId(rs.getInt("feedMetaDataId"));
		feedMetaData.setComment(rs.getString("comment"));
		feedMetaData.setIsLike(rs.getBoolean("isLike"));
		feedMetaData.setCreatedOn(rs.getTimestamp("createdOn"));

		return feedMetaData;
	}

}
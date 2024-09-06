package com.bptn.feedAppAutomation.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bptn.feedAppAutomation.bean.Feed;
import com.bptn.feedAppAutomation.bean.User;
import com.bptn.feedAppAutomation.dao.mapper.FeedMapper;


@Repository
public class FeedDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Feed getLatestFeedByUser(User user) {

		String sql = "SELECT * FROM \"Feed\" WHERE \"userId\" = ? ORDER BY \"feedId\" DESC LIMIT 1";

		return this.jdbcTemplate.queryForObject(sql, new FeedMapper(), user.getUserId());
	}

	public Page<Feed> findByUser(User user, Pageable pageable) {

		String sql = "SELECT * FROM \"Feed\" WHERE \"userId\" = ? ORDER BY \"feedId\" DESC LIMIT ? OFFSET ?";

		Object[] params = { user.getUserId(), pageable.getPageSize(), pageable.getOffset() };

		List<Feed> feeds = this.jdbcTemplate.query(sql, new FeedMapper(), params);

		int totalCount = this.getTotalFeedCountForUser(user);

		return new PageImpl<>(feeds, pageable, totalCount);
	}

	private int getTotalFeedCountForUser(User user) {

		String sql = "SELECT COUNT(*) FROM \"Feed\" WHERE \"userId\" = ?";

		return this.jdbcTemplate.queryForObject(sql, Integer.class, user.getUserId());
	}

}
package com.bptn.feedAppAutomation.bean;

import java.sql.Timestamp;

public class FeedMetaData {
	
	private Integer feedMetaDataId;
	private String comment;
	private Timestamp createdOn;
	private Boolean isLike;
	
	
	public Integer getFeedMetaDataId() {
		return feedMetaDataId;
	}
	public void setFeedMetaDataId(Integer feedMetaDataId) {
		this.feedMetaDataId = feedMetaDataId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Boolean getIsLike() {
		return isLike;
	}
	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}
	
	

}

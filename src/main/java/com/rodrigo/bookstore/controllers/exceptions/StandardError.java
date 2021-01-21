package com.rodrigo.bookstore.controllers.exceptions;

import java.util.List;

public class StandardError {
	private Integer status;
	private List<String> messages;
	private Long timestamp;
	
	public StandardError() {
		super();
	}

	public StandardError(Integer status, List<String> messages, Long timestamp) {
		this.status = status;
		this.messages = messages;
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}

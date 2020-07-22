package io.shubham.resource;

import java.time.LocalDateTime;

public class Url {

	private String full_url;
	private String short_url;
	private LocalDateTime created;
	public String getFull_url() {
		return full_url;
	}

	public void setFull_url(String full_url) {
		this.full_url = full_url;
	}

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

}
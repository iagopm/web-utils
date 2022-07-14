package iagopm.web.model;

import java.io.Serializable;

public class Link implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6663248308310774493L;
	private String url;

	public Link() {
	}

	public Link(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
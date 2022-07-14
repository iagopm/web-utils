package iagopm.web.model;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3494476846061116634L;
	private String text;

	public Message() {
	}

	public Message(String text) {
		this.text = text;
	}

	public String getMessage() {
		return text;
	}

	public void setMessage(String text) {
		this.text = text;
	}
}
package iagopm.web.model;

import java.io.Serializable;

public class Search implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6139800524418471795L;
	private String text;

	public Search() {
	}

	public Search(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
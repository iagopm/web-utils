package iagopm.web.model;

public class Token {
	private String value;

	public Token() {
	}

	public Token(String token) {
		this.value = token;
	}

	public String getToken() {
		return value;
	}

	public void setToken(String token) {
		this.value = token;
	}
}
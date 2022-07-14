package iagopm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import iagopm.web.model.Message;
import iagopm.web.model.Token;
import iagopm.web.service.TokenService;

@RestController
public class AuthenticationController {
	@Autowired
	private TokenService tokenService;

	@PostMapping(path = "/getToken", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Token> getToken(@RequestParam("user") String username,
			@RequestParam("password") String password) {
		Token token = tokenService.generateToken(username, password);
		if (token != null) {
			return ResponseEntity.ok(token);
		} else {
			return ResponseEntity.badRequest().body(new Token("Invalid Credentials"));
		}
	}

	@PostMapping(path = "/invalidToken")
	@ResponseBody
	public ResponseEntity<Message> invalidToken() {
		return ResponseEntity.badRequest().body(new Message("Invalid Credentials"));
	}
}
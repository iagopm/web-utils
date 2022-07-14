package iagopm.web.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import iagopm.web.model.Token;
@Service
public interface TokenService extends InitializingBean {
	public boolean isValid(String token);
	public Token generateToken(String username,String password);
}
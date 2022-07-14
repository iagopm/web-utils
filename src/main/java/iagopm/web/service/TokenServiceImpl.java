package iagopm.web.service;

import java.security.Key;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import iagopm.web.model.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenServiceImpl implements TokenService,InitializingBean{
	private static Logger logger = LogManager.getLogger(TokenServiceImpl.class);

	@Value("${secretKey}")
	private String secretKey;
	@Value("${appId}")
	private String appId;
	@Value("${adminUser}")
	private String adminUser;
	@Value("${adminPassword}")
	private String adminPassword;
	
	private List<GrantedAuthority> grantedAuthorities = null;
	private Key key = null;

	@Override
	public boolean isValid(String token) {
		try {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject()
				.equals(adminUser);
		} catch (Exception e) {
			logger.warn("Invalid token",e);
			return false;
		}
	}

	@Override
	public Token generateToken(String username, String password) {
		if (username.equals(adminUser) && password.equals(adminPassword)) {
			ZonedDateTime date = ZonedDateTime.now();
			return new Token(Jwts
					.builder()
					.setId(appId)
					.setSubject(username)
					.claim("authorities",
							grantedAuthorities.stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))
					.setIssuedAt(Date.from(date.toInstant()))
					.setExpiration(Date.from(ChronoUnit.DAYS.addTo(date, 2).toInstant()))
					.signWith(key)
					.compact());
		}else {
			return null;
		}
	}

	@Override
	public void afterPropertiesSet(){
		grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}
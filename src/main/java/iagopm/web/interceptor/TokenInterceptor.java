package iagopm.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import iagopm.web.service.TokenService;

public class TokenInterceptor implements HandlerInterceptor {
	private static Logger logger = LogManager.getLogger(TokenInterceptor.class);
	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestTokenHeader = request.getHeader("Authorization");

		if (StringUtils.isNotBlank(requestTokenHeader)) {
			requestTokenHeader = requestTokenHeader.replace("Bearer ", "");
			if (tokenService.isValid(requestTokenHeader)) {
				logger.info("Valid token -> {}", requestTokenHeader);
				return HandlerInterceptor.super.preHandle(request, response, handler);
			} else {
				request.getRequestDispatcher("/invalidToken").forward(request, response);
				return false;
			}
		} else {
			request.getRequestDispatcher("/invalidToken").forward(request, response);
			return false;
		}
	}
}
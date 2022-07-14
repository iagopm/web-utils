package iagopm.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.stereotype.Component;

import iagopm.web.model.Spoof;
@Component
@Order(1)
public class SpoofFilter implements Filter {
	private static Logger logger = LogManager.getLogger(SpoofFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Spoof spoof = new Spoof((HttpServletRequest) request);
		logger.info(spoof);
		chain.doFilter(request, response);
	}
}

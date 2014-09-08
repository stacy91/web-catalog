package com.helpers.globalexceptionconfig;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {
	
	private Logger log = Logger.getLogger("exceptionsLogger");
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		super.setDefaultFailureUrl("/home?login_error=t");
		super.onAuthenticationFailure(request, response, exception);
		log.error("Bad Credentials",exception);
	}
	
}
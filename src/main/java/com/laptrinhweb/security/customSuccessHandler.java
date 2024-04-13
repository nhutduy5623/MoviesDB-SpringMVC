package com.laptrinhweb.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.laptrinhweb.util.SecurityUtil;

@Component
public class customSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private String determineTargetUrl(Authentication authentication) {
		String url = "";
		List<String> roles = SecurityUtil.getAuthorities();
		if (isAdmin(roles)) {
			url = "/admin/home";
		} else {
			url = "/home";
		}
		return url;
	}
	
	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ADMIN_HOME")) {
			return true;
		}
		return false;		
	}
	
}

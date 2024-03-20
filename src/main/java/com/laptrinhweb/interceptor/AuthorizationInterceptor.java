package com.laptrinhweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.service.impl.AuthorizationService;
import com.laptrinhweb.util.SecurityUtil;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

	@Autowired
	private AuthorizationService authorizationService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		try {
			Long currentUserId = SecurityUtil.getPrincipal().getId();
			if (authorizationService.checkAuthorization(currentUserId, url)) {
				return true;
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
				return false;
			}			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/home");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Các thao tác sau khi Controller đã xử lý request
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Các thao tác sau khi response đã được gửi đi
	}

}

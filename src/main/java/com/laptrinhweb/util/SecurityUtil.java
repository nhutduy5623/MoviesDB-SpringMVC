package com.laptrinhweb.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.laptrinhweb.dto.userSecurity;

public class SecurityUtil {

	// Get UserSecurity
	public static userSecurity getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof userSecurity) {
	        return (userSecurity) authentication.getPrincipal();
	    } else {
	        // Người dùng chưa đăng nhập hoặc không có đối tượng Principal hoặc không phải là userSecurity
	        return null; // hoặc throw một exception tùy thuộc vào logic của ứng dụng của bạn
	    }
	}

	// Get Tất cả các role của User đã thành công đăng nhập.
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<>();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		for (GrantedAuthority authority : authorities)
			results.add(authority.getAuthority());
		return results;
	}
	
	

}

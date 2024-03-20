package com.laptrinhweb.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.laptrinhweb.dto.userSecurity;

public class SecurityUtil {

	// Get UserSecurity
	public static userSecurity getPrincipal() {
		userSecurity userSecurity = (userSecurity) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		return userSecurity;
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

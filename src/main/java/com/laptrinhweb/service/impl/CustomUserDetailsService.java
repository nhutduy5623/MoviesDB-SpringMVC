package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laptrinhweb.dto.userSecurity;
import com.laptrinhweb.entity.RoleEntity;
import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByEmail(email);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException("User Not Found!");			
		} else {
			if(userEntity.getStatus() == 0) {
				throw new UsernameNotFoundException("Account has been locked!");	
			} else {
				//Nếu đúng thông tin thì sẽ tạo session(ở đây sẽ lưu vào là userSecurity(extent từ User) giống như session user bên Servlet)
				List<GrantedAuthority> authorities = new ArrayList<>();
				for(RoleEntity role: userEntity.getRoleList()) {
					authorities.add(new SimpleGrantedAuthority(role.getCode()));
				}
				userSecurity userSecurity = new userSecurity(userEntity.getId(), userEntity.getEmail(), userEntity.getPassWord(), userEntity.getFullName(), userEntity.getCode(), true, true, true, true, authorities);
				return userSecurity;
			}			
		}		
	}	

}

package com.laptrinhweb.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.entity.PermissionEntity;
import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.repository.IUserRepository;


@Service
public class AuthorizationService{
	
	@Autowired 
	IUserRepository userRepository;
	
	public boolean checkAuthorization(Long userId, String Url) {
		try {
			URI uri = new URI(Url);
			String path = uri.getPath();
			int adminIndex = path.indexOf("/admin/");
            String desiredPath = path.substring(adminIndex);        
            desiredPath = desiredPath.split("\\?")[0];
//            Optional<UserEntity> user = userRepository.findUserAuthorization(userId, desiredPath);
//            if(user.isPresent())
//    			return true;
            List<PermissionEntity> userPermissions = userRepository.findUserPermissions(userId); 
            for(PermissionEntity permission : userPermissions) {
            	System.out.println("URL="+desiredPath+"  PermissionUrl="+permission.getFunctionURL());
            	if(desiredPath.contains(permission.getFunctionURL()))
            		return true;
            }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}				
		return false;
	}
}

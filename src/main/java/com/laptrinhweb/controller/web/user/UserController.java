package com.laptrinhweb.controller.web.user;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.service.IUserService;
import com.laptrinhweb.util.SecurityUtil;

@Controller(value = "userController")
public class UserController {

	@Autowired
	IUserService userService;
	SecurityUtil securityUtil = new SecurityUtil();
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profilePage() {
		ModelAndView mav = new ModelAndView("web/user/userProfile");
		mav.addObject("model", userService.findOne(SecurityUtil.getPrincipal().getId()));
		return mav;
	}

	@PostMapping("/register")
	@ResponseBody
	public String register(@RequestBody UserDTO user) {
		Map<String, Boolean> data = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		Boolean isCorrect = true;
		if (!user.getPassWord().equals(user.getReTypePassword())) {
			data.put("notMatchPassword", true);
			isCorrect = false;
		}
		if (user.getPassWord().length() < 6) {
			data.put("passwordVeryShort", true);
			isCorrect = false;
		}
		if (userService.countByEmail(user.getEmail()) > 0) {
			data.put("existEmail", true);
			isCorrect = false;
		}
		if (isCorrect) {
			data.put("success", true);
			user.setCode(user.getEmail());
			user.setAvatar("/template/web/images/uploads/author2.png");
			userService.save(user);
		}
		try {
			return objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@PostMapping("/change-password")
	@ResponseBody
	public String changePassword(@RequestBody UserDTO user) throws InterruptedException {
		Map<String, Boolean> data = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		UserDTO userInContext = userService.findOne(SecurityUtil.getPrincipal().getId());
		Boolean isCorrect = true;

		if (!passwordEncoder.matches(user.getOldPassword(), userInContext.getPassWord())) {
			data.put("notMatchOldPassword", true);
			isCorrect = false;
		}
		if (!user.getPassWord().equals(user.getReTypePassword())) {
			data.put("notMatchPassword", true);
			isCorrect = false;
		}
		if (user.getPassWord().length() < 6) {
			data.put("passwordVeryShort", true);
			isCorrect = false;
		}
		if (isCorrect) {
			data.put("success", true);
			userInContext.setPassWord(user.getPassWord());
			userService.save(userInContext);
		}
		try {
			return objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@PostMapping("/change-info")
	@ResponseBody
	public String changeInfo(@RequestBody UserDTO user) throws InterruptedException {
		Map<String, Boolean> data = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		UserDTO userInContext = userService.findOne(SecurityUtil.getPrincipal().getId());
		data.put("success", true);
		userInContext.setFullName(user.getFullName());
		userService.save(userInContext);
		SecurityUtil.getPrincipal().setFullName(user.getFullName());
		try {
			return objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@PostMapping("/change-avatar")
	@ResponseBody
	public String changeAvatar(@RequestBody String avatarBase64) throws InterruptedException {
		Map<String, Boolean> data = new HashMap<>();
		UserDTO userInContext = userService.findOne(SecurityUtil.getPrincipal().getId());
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String uploadToServerDir = servletContext.getRealPath("/") + "template\\web\\images\\uploads\\";
			String fileName = "user-avatar-" + userInContext.getId()+".jpg";
			// Project must in the eclip-workspace folder
			String pathFolderProject = uploadToServerDir.substring(0, uploadToServerDir.indexOf("\\.metadata\\"))
					+ "\\MoviesDB_springMVC\\src\\main\\webapp\\template\\web\\images\\uploads\\";
			
			// Chuyển đổi base64Image thành byte[]
			byte[] imageBytes = DatatypeConverter.parseBase64Binary(avatarBase64.replaceAll("data:image/.+;base64,", ""));
			
			// Lưu hình ảnh vào thư mục server
            Path path = new File(uploadToServerDir).toPath();
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.createDirectories(path.getParent());
            Files.write(path.resolve(fileName), imageBytes);
            
            // Lưu hình ảnh vào thư mục project
            path = new File(pathFolderProject).toPath();
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.createDirectories(path.getParent());
            Files.write(path.resolve(fileName), imageBytes);
            
            userInContext.setAvatar("/template/web/images/uploads/"+fileName);
            userService.save(userInContext);
            data.put("success", true);
			return objectMapper.writeValueAsString(data);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
}
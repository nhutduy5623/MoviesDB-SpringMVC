package com.laptrinhweb.controller.admin.authorization;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.RoleDTO;
import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.service.IRoleService;
import com.laptrinhweb.service.IUserService;

@Controller(value = "userController_admin")
public class userController {
	@Autowired
	IUserService userService;
	
	@Autowired
	IRoleService roleService;

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String search) {
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		}
		Pageable pageable = new PageRequest(page-1, limit);
		UserDTO userDTO = new UserDTO();
		userDTO.setLimit(limit);
		if(search == null) { //FindALL
			userDTO.setListResults(userService.findAll(pageable));
			userDTO.setTotalPages((int) Math.ceil((double) userService.countAll()/userDTO.getLimit()));
		} else { //Find By Search Value
			userDTO.setListResults(userService.findByEmailPageable(search, pageable));
			userDTO.setTotalPages((int) Math.ceil((double) userService.countByEmail(search)/userDTO.getLimit()));
			userDTO.setSearchValue(search);
		}
		if(userDTO.getTotalPages()==0) {
			userDTO.setTotalPages(1);
		}
		userDTO.setNextPage(page);	
		ModelAndView mav = new ModelAndView("admin/user/userManage");
		mav.addObject("model", userDTO);
		return mav;
	}

	@RequestMapping(value = "/admin/user/save", method = RequestMethod.GET)
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/user/user_formEdit_Save");
		mav.addObject("model", new UserDTO());
		mav.addObject("roleCodeList", roleService.findAll_HasMap());
		return mav;
	}

	@RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/user_formEdit_Save");
		UserDTO userDTO = userService.findOne(id);
		mav.addObject("model", userDTO);
		mav.addObject("roleCodeList", roleService.findAll_HasMap());
		return mav;
	}

}

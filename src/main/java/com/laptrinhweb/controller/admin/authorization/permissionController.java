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

import com.laptrinhweb.dto.PermissionDTO;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IPermissionService;
import com.laptrinhweb.service.IRoleService;
import com.laptrinhweb.service.ISubGenreService;

@Controller(value = "permissionController_admin")
public class permissionController {
	
	@Autowired
	IPermissionService permissionService;
	
	@Autowired
	IRoleService roleService;
	
	@Autowired
	IGenreService genreService;
	
	@Autowired
	ISubGenreService subGenreService;

	@RequestMapping(value = "/admin/permission", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String search) {
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		} 
		Pageable pageable = new PageRequest(page-1, limit);
		PermissionDTO permissionDTO = new PermissionDTO();
		permissionDTO.setLimit(limit);
		if(search == null) {
			permissionDTO.setListResults(permissionService.findAll(pageable));
			permissionDTO.setTotalPages(permissionService.countAll());
			permissionDTO.setTotalPages((int) Math.ceil((double) permissionService.countAll()/permissionDTO.getLimit()));
		} else {
			permissionDTO.setSearchValue(search);
			permissionDTO.setListResults(permissionService.findByNamePageable(search, pageable));
			permissionDTO.setTotalPages((int) Math.ceil((double) permissionService.countByName(search)/permissionDTO.getLimit()));
		}
		if(permissionDTO.getTotalPages()==0) {
			permissionDTO.setTotalPages(1);
		}
		permissionDTO.setNextPage(page);		
		ModelAndView mav = new ModelAndView("admin/authorization/permissionManage");
		mav.addObject("model", permissionDTO);
		return mav;
	}

	@RequestMapping(value = "/admin/permission/save", method = RequestMethod.GET)
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/authorization/permission_formEdit_Save");
		mav.addObject("model", new PermissionDTO());
		mav.addObject("roleCodeList", roleService.findAll_HasMap());
		return mav;
	}

	@RequestMapping(value = "/admin/permission/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/authorization/permission_formEdit_Save");
		PermissionDTO permissionDTO = permissionService.findOne(id);
		mav.addObject("model", permissionDTO);
		mav.addObject("roleCodeList", roleService.findAll_HasMap());
		return mav;
	}

}

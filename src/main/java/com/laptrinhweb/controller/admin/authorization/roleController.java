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

import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.dto.RoleDTO;
import com.laptrinhweb.service.IPermissionService;
import com.laptrinhweb.service.IRoleService;

@Controller(value = "roleController_admin")
public class roleController {
	@Autowired
	IPermissionService permissionService;
	
	@Autowired
	IRoleService roleService;

	@RequestMapping(value = "/admin/role", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String search) {
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		}
		Pageable pageable = new PageRequest(page-1, limit);
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setLimit(limit);
		if(search == null) { //FindALL
			roleDTO.setListResults(roleService.findAll(pageable));
			roleDTO.setTotalPages((int) Math.ceil((double) roleService.countAll()/roleDTO.getLimit()));
		} else { //Find By Search Value
			roleDTO.setListResults(roleService.findByNamePageable(search, pageable));
			roleDTO.setTotalPages((int) Math.ceil((double) roleService.countByName(search)/roleDTO.getLimit()));
			roleDTO.setSearchValue(search);
		}
		if(roleDTO.getTotalPages()==0) {
			roleDTO.setTotalPages(1);
		}
		roleDTO.setNextPage(page);	
		ModelAndView mav = new ModelAndView("admin/authorization/roleManage");
		mav.addObject("model", roleDTO);
		return mav;
	}

	@RequestMapping(value = "/admin/role/save", method = RequestMethod.GET)
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/authorization/role_formEdit_Save");
		mav.addObject("model", new RoleDTO());
		mav.addObject("permissionCodeList", permissionService.findAll_HasMap());
		return mav;
	}

	@RequestMapping(value = "/admin/role/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/authorization/role_formEdit_Save");
		RoleDTO roleDTO = roleService.findOne(id);
		mav.addObject("model", roleDTO);
		mav.addObject("permissionCodeList", permissionService.findAll_HasMap());
		return mav;
	}

}

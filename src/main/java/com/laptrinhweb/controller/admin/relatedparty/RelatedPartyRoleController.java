package com.laptrinhweb.controller.admin.relatedparty;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.RelatedPartyRoleDTO;
import com.laptrinhweb.service.IRelatedPartyRoleService;

@Controller(value = "relatedPartyRoleController_admin")
public class RelatedPartyRoleController {
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;

	@GetMapping("/admin/relatedpartyrole")
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String search) {
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		}
		Pageable pageable = new PageRequest(page-1, limit);
		RelatedPartyRoleDTO relatedPartyRoleDTO = new RelatedPartyRoleDTO();
		relatedPartyRoleDTO.setLimit(limit);
		if(search == null) { //FindALL
			relatedPartyRoleDTO.setListResults(relatedPartyRoleService.findAll(pageable));
			relatedPartyRoleDTO.setTotalPages((int) Math.ceil((double) relatedPartyRoleService.countAll()/relatedPartyRoleDTO.getLimit()));
		} else { //Find By Search Value
			relatedPartyRoleDTO.setListResults(relatedPartyRoleService.findByNamePageable(search, pageable));
			relatedPartyRoleDTO.setTotalPages((int) Math.ceil((double) relatedPartyRoleService.countByName(search)/relatedPartyRoleDTO.getLimit()));
			relatedPartyRoleDTO.setSearchValue(search);
		}
		if(relatedPartyRoleDTO.getTotalPages()==0) {
			relatedPartyRoleDTO.setTotalPages(1);
		}
		relatedPartyRoleDTO.setNextPage(page);	
		ModelAndView mav = new ModelAndView("admin/relatedPartyRole/relatedPartyRoleManage");
		mav.addObject("model", relatedPartyRoleDTO);
		return mav;
	}

	@GetMapping("/admin/relatedpartyrole/save")
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/relatedPartyRole/formEdit_Save");
		mav.addObject("model", new RelatedPartyRoleDTO());
		return mav;
	}

	@GetMapping("/admin/relatedpartyrole/edit")
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/relatedPartyRole/formEdit_Save");
		RelatedPartyRoleDTO relatedPartyRoleDTO = relatedPartyRoleService.findOne(id);
		mav.addObject("model", relatedPartyRoleDTO);
		return mav;
	}

}

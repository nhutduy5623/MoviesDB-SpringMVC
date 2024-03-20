package com.laptrinhweb.controller.admin.relatedparty;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.IRelatedPartyService;

@Controller(value = "relatedPartyController_admin")
public class RelatedPartyController {
	@Autowired
	IRelatedPartyService relatedPartyServ;
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleServ;
	
	@GetMapping("/admin/relatedparty")
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "roleCode", required = false) String roleCode, 
			@RequestParam(value = "search", required = false) String search) {
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		} 
		if (roleCode == null) {
			roleCode = "-";
		}
		Pageable pageable = new PageRequest(page-1, limit);
		RelatedPartyDTO relatedPartyDTO = new RelatedPartyDTO();
		relatedPartyDTO.setLimit(limit);
		if(roleCode.equals("-")) {
			if(search == null) {
				relatedPartyDTO.setListResults(relatedPartyServ.findAll(pageable));
				relatedPartyDTO.setTotalPages(relatedPartyServ.countAll());
				relatedPartyDTO.setTotalPages((int) Math.ceil((double) relatedPartyServ.countAll()/relatedPartyDTO.getLimit()));
			} else {
				relatedPartyDTO.setSearchValue(search);
				relatedPartyDTO.setListResults(relatedPartyServ.findByNamePageable(search, pageable));
				relatedPartyDTO.setTotalPages((int) Math.ceil((double) relatedPartyServ.countByName(search)/relatedPartyDTO.getLimit()));
			}
		}else {
			relatedPartyDTO.setListResults(relatedPartyServ.findByRelatedPartyRoleCode(roleCode, pageable));
			relatedPartyDTO.setTotalPages((int) Math.ceil((double) relatedPartyServ.countByRelatedPartyRoleCode(roleCode)/relatedPartyDTO.getLimit()));			
		}
		if(relatedPartyDTO.getTotalPages()==0) {
			relatedPartyDTO.setTotalPages(1);
		}
		relatedPartyDTO.setNextPage(page);		
		ModelAndView mav = new ModelAndView("admin/relatedParty/relatedPartyManage");
		mav.addObject("model", relatedPartyDTO);
		mav.addObject("roleCode", roleCode);
		mav.addObject("roleList", relatedPartyRoleServ.findAll());
		return mav;
	}

	@GetMapping("/admin/relatedparty/save")
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/relatedParty/formEdit_Save");
		mav.addObject("model", new RelatedPartyDTO());
		mav.addObject("roleList", relatedPartyRoleServ.findAll());
		return mav;
	}

	@GetMapping("/admin/relatedparty/edit")
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/relatedParty/formEdit_Save");
		RelatedPartyDTO relatedPartyDTO = relatedPartyServ.findOne(id);
		mav.addObject("model", relatedPartyDTO);
		mav.addObject("roleList", relatedPartyRoleServ.findAll());
		return mav;
	}

}

package com.laptrinhweb.controller.web.relatedparty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.RelatedPartyDTO;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.IRelatedPartyService;
import com.laptrinhweb.service.ISubGenreService;
import com.laptrinhweb.service.IWorkService;
import com.laptrinhweb.service.impl.RelatedPartyWorkDetailService;

@Controller(value = "relatedPartyController")
public class RelatedPartyController{
	
	@Autowired
	IWorkService workService;
	
	@Autowired
	ISubGenreService subGenreService;
	
	@Autowired
	IGenreService genreService;
	
	@Autowired
	IRelatedPartyService relatedPartyServ;
	
	@Autowired
	RelatedPartyWorkDetailService relatedPartyWorkDetailService;
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;
	
	@RequestMapping(value = "/relatedparty", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "roleCode", required = false) String roleCode, 
			@RequestParam(value = "search", required = false) String search
			) {
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		} 
		if (roleCode==null) {
			roleCode="-";
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
		ModelAndView mav = new ModelAndView("web/relatedparty/gridRelatedParty");
		mav.addObject("model", relatedPartyDTO);
		mav.addObject("roleCode", roleCode);
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		mav.addObject("genreList", genreService.findAll());
		return mav;
	}
	@GetMapping("/relatedparty/details")
	public ModelAndView detailPage(@RequestParam(value = "code") String code) {
		ModelAndView mav = new ModelAndView("web/relatedparty/relatedPartySingle");
		mav.addObject("model", relatedPartyServ.findOneByCode(code));
		mav.addObject("workList", workService.findByRelatedPartyCode(code));
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		return mav;
	}

}

package com.laptrinhweb.controller.web.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.ViewerVoteDetailFullDTO;
import com.laptrinhweb.dto.userSecurity;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.IUserService;
import com.laptrinhweb.service.IViewerVoteDetailService;
import com.laptrinhweb.util.SecurityUtil;

@Controller(value = "userRateController")
public class rateController {
	@Autowired
	IGenreService genreService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;
	
	@Autowired
	IViewerVoteDetailService voteService;
	
	@RequestMapping(value = "/userrate", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		if (page == null) {
			page = 1;
		}
		if (limit == null) {
			limit = 16;
		} 
		userSecurity currentUser = SecurityUtil.getPrincipal();
		
		Pageable pageable = new PageRequest(page-1, limit);
		ViewerVoteDetailFullDTO voteDTO = new ViewerVoteDetailFullDTO();
		
		voteDTO.setLimit(limit);
		voteDTO.setListResults(voteService.findByUserPageable(currentUser.getId(), pageable));
		long size = voteService.countByUser(currentUser.getId());
		
		voteDTO.setTotalPages((int) Math.ceil((double) size/limit));
		voteDTO.setNextPage(page+1);
		ModelAndView mav = new ModelAndView("web/user/rateList");
		mav.addObject("model", voteDTO);
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("size", size);
		mav.addObject("currentUser", userService.findOne(currentUser.getId()));
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		return mav;
	}
}
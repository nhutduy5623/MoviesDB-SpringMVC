package com.laptrinhweb.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.entity.WorkEntity;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.ISubGenreService;
import com.laptrinhweb.service.IWorkService;

@Controller(value = "homeController_web")
public class homeController {
	
	@Autowired
	IGenreService genreService;
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;
	
	@Autowired
	IWorkService workService;
	
	@Autowired
	ISubGenreService subgenreService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		List<WorkDTO> Top5ByScore = workService.findTopByScore(5);
		mav.addObject("Top5ByScore", Top5ByScore);	
		List<String> list_WorkCode = new ArrayList<>();
		for(WorkDTO work: Top5ByScore) 
			list_WorkCode.add(work.getCode());		
		mav.addObject("Map_Subgenres", subgenreService.findByWorkCode_HasMap(list_WorkCode));	
		mav.addObject("Top7ByScore", workService.findTopByScore(7));		
		mav.addObject("Top7ByVoteCount", workService.findTopByVoteCount(7));	
		mav.addObject("Top7ByRelatedDate", workService.findTopByRelatedDate(7));
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView homePageLogin() {
		ModelAndView mav = new ModelAndView("redirect:/home");
		return mav;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		ModelAndView mav = new ModelAndView("redirect:/home");
		return mav;
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/home");
		return mav;
	}
}

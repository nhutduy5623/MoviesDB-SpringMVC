package com.laptrinhweb.controller.web.favorite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.UserFavoriteDetailFULLDTO;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.dto.userSecurity;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IRelatedPartyDetailService;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.IRelatedPartyService;
import com.laptrinhweb.service.ISubGenreService;
import com.laptrinhweb.service.IUserFavoriteDetailService;
import com.laptrinhweb.service.IUserService;
import com.laptrinhweb.service.IWorkService;
import com.laptrinhweb.util.SecurityUtil;

@Controller(value = "favoriteController")
public class favoriteController {
	
	@Autowired
	IWorkService workService;
	
	@Autowired
	ISubGenreService subGenreService;
	
	@Autowired
	IUserFavoriteDetailService userFavoriteDetailService;
	
	@Autowired
	IGenreService genreService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IRelatedPartyService relatedPartyService;
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;
	
	@Autowired
	IRelatedPartyDetailService relatedPartyDetailService;
	
	
	
	@RequestMapping(value = "/userfavorite", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "genreCode", required = false) String genreCode			
			) {
		if (page == null || limit == null) {
			page = 1;
			limit = 16;
		} 		
		userSecurity currentUser = SecurityUtil.getPrincipal();
		System.out.println(currentUser.getCode());
//
		Pageable pageable = new PageRequest(page-1, limit);
		UserFavoriteDetailFULLDTO userFvFullDTO = new UserFavoriteDetailFULLDTO();
		userFvFullDTO.setLimit(limit);
		if(genreCode!=null && genreCode.equals("-")) 
			genreCode = null;
		userFvFullDTO.setListResults(userFavoriteDetailService.findByUserCodeAndGenreCode(currentUser.getCode(), genreCode, pageable));
		Integer size = userFavoriteDetailService.countByUserCodeAndGenreCode(currentUser.getCode(), genreCode);
		userFvFullDTO.setTotalPages((int) Math.ceil((double) size/userFvFullDTO.getLimit()));
		if(userFvFullDTO.getTotalPages()==0) {
			userFvFullDTO.setTotalPages(1);
		}
		if(genreCode==null)
			genreCode="-";
		userFvFullDTO.setNextPage(page);		
		ModelAndView mav = new ModelAndView("web/user/favoriteList");
		mav.addObject("model", userFvFullDTO);
		mav.addObject("genreCode", genreCode);
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("size", size);
		mav.addObject("currentUser", userService.findOne(currentUser.getId()));
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		return mav;
	}
	

	
//	
//	
//	@RequestMapping(value = "/work/detail", method = RequestMethod.GET)
//	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
//			@RequestParam(value = "code", required = true) String code
//			) {
//		ModelAndView mav = new ModelAndView("web/work/workDetail");
//		WorkDTO workDTO = workService.findOneByCode(code);	
//		List<SubGenreDTO> subgenreList = subGenreService.findByWorkCode(code);
//		GenreDTO genreDTO = genreService.findOneByCode(workDTO.getGenreCode());
//		mav.addObject("model", workDTO);
//		mav.addObject("subGenreList", subgenreList);
//		mav.addObject("genre",genreDTO);
//		mav.addObject("genreList", genreService.findAll());
//		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
//		mav.addObject("relatedPartyDetailList", relatedPartyDetailService.findRPDetailByWork(workDTO));
//		return mav;
//	}
}

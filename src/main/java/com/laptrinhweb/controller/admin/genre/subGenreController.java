package com.laptrinhweb.controller.admin.genre;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.ISubGenreService;

@Controller(value = "subGenreController_admin")
public class subGenreController {
	@Autowired
	ISubGenreService subGenreService;
	
	@Autowired
	IGenreService genreService;

	@RequestMapping(value = "/admin/subgenre", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "genreCode", required = false) String genreCode, 
			@RequestParam(value = "search", required = false) String search) {
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		} 
		if (genreCode == null) {
			genreCode = "-";
		}
		Pageable pageable = new PageRequest(page-1, limit);
		SubGenreDTO subGenreDTO = new SubGenreDTO();
		subGenreDTO.setLimit(limit);
		if(genreCode.equals("-")) {
			if(search == null) {
				subGenreDTO.setListResults(subGenreService.findAll(pageable));
				subGenreDTO.setTotalPages(subGenreService.countAll());
				subGenreDTO.setTotalPages((int) Math.ceil((double) subGenreService.countAll()/subGenreDTO.getLimit()));
			} else {
				subGenreDTO.setSearchValue(search);
				subGenreDTO.setListResults(subGenreService.findByNamePageable(search, pageable));
				subGenreDTO.setTotalPages((int) Math.ceil((double) subGenreService.countByName(search)/subGenreDTO.getLimit()));
			}
		}else {
			subGenreDTO.setListResults(subGenreService.findByGenreList_Code(genreCode, pageable));
			subGenreDTO.setTotalPages((int) Math.ceil((double) subGenreService.countByGenreList_Code(genreCode)/subGenreDTO.getLimit()));			
		}
		if(subGenreDTO.getTotalPages()==0) {
			subGenreDTO.setTotalPages(1);
		}
		subGenreDTO.setNextPage(page);		
		ModelAndView mav = new ModelAndView("admin/genre/subGenreManage");
		mav.addObject("model", subGenreDTO);
		mav.addObject("genreCode", genreCode);
		mav.addObject("genreList", genreService.findAll());
		return mav;
	}

	@RequestMapping(value = "/admin/subgenre/save", method = RequestMethod.GET)
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/genre/subGenre_formEdit_Save");
		mav.addObject("model", new SubGenreDTO());
		mav.addObject("genreCodeList", genreService.findAll_HasMap());
		return mav;
	}

	@RequestMapping(value = "/admin/subgenre/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/genre/subGenre_formEdit_Save");
		SubGenreDTO subGenreDTO = subGenreService.findOne(id);
		mav.addObject("model", subGenreDTO);
		mav.addObject("genreCodeList", genreService.findAll_HasMap());
		return mav;
	}

}

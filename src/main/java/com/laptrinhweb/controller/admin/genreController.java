package com.laptrinhweb.controller.admin;

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
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.ISubGenreService;

@Controller(value = "genreController_admin")
public class genreController {
	@Autowired
	IGenreService genreService;
	
	@Autowired
	ISubGenreService subGenreService;

	@RequestMapping(value = "/admin/genre", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String search) {
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		}
		Pageable pageable = new PageRequest(page-1, limit);
		GenreDTO genreDTO = new GenreDTO();
		genreDTO.setLimit(limit);
		if(search == null) { //FindALL
			genreDTO.setListResults(genreService.findAll(pageable));
			genreDTO.setTotalPages((int) Math.ceil((double) genreService.countAll()/genreDTO.getLimit()));
		} else { //Find By Search Value
			genreDTO.setListResults(genreService.findByNamePageable(search, pageable));
			genreDTO.setTotalPages((int) Math.ceil((double) genreService.countByName(search)/genreDTO.getLimit()));
			genreDTO.setSearchValue(search);
		}
		if(genreDTO.getTotalPages()==0) {
			genreDTO.setTotalPages(1);
		}
		genreDTO.setNextPage(page);	
		ModelAndView mav = new ModelAndView("admin/genre/genreManage");
		mav.addObject("model", genreDTO);
		return mav;
	}

	@RequestMapping(value = "/admin/genre/save", method = RequestMethod.GET)
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/genre/formEdit_Save");
		mav.addObject("model", new GenreDTO());
		mav.addObject("subGenreCodeList", subGenreService.finAll_HasMap());
		return mav;
	}

	@RequestMapping(value = "/admin/genre/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/genre/formEdit_Save");
		GenreDTO genreDTO = genreService.findOne(id);
		mav.addObject("model", genreDTO);
		mav.addObject("subGenreCodeList", subGenreService.finAll_HasMap());
		return mav;
	}

}

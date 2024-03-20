package com.laptrinhweb.controller.web.genre;

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
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.ISubGenreService;

@Controller(value = "genreController")
public class genreController {
	
	@Autowired
	IGenreService genreService;
	
	@Autowired
	ISubGenreService subGenreService;
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;
	
	@RequestMapping(value = "/genre", method = RequestMethod.GET)
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
			genreDTO.setListResults(genreService.findAll());
		}
//			genreDTO.setTotalPages((int) Math.ceil((double) genreService.countAll()/genreDTO.getLimit()));
//		} else { //Find By Search Value
//			genreDTO.setListResults(genreService.findByNamePageable(search, pageable));
//			genreDTO.setTotalPages((int) Math.ceil((double) genreService.countByName(search)/genreDTO.getLimit()));
//			genreDTO.setSearchValue(search);
//		}
//		if(genreDTO.getTotalPages()==0) {
//			genreDTO.setTotalPages(1);
//		}
//		genreDTO.setNextPage(page);	
		ModelAndView mav = new ModelAndView("web/genre/gridGenre");
		mav.addObject("model", genreDTO);
//		mav.addObject("listSubgenre", subGenreService.findAll_HasMap());
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		return mav;
	}
}

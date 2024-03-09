package com.laptrinhweb.controller.admin.serie;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.dto.SerieDTO;
import com.laptrinhweb.service.ISerieService;

@Controller
public class serieController {
	@Autowired private ISerieService serieSrv;
	
	@GetMapping("/admin/serie")
	public ModelAndView homePage(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String search) {
		
		
		if (page == null || limit == null) {
			page = 1;
			limit = 5;
		}
		Pageable pageable = new PageRequest(page-1, limit);
		SerieDTO serieDTO = new SerieDTO();
		serieDTO.setLimit(limit);
		if(search == null) { //FindALL
			serieDTO.setListResults(serieSrv.findAll(pageable));
			serieDTO.setTotalPages((int) Math.ceil((double) serieSrv.countAll()/serieDTO.getLimit()));
		} else { //Find By Search Value
			serieDTO.setListResults(serieSrv.findByNamePageable(search, pageable));
			serieDTO.setTotalPages((int) Math.ceil((double) serieSrv.countByName(search)/serieDTO.getLimit()));
			serieDTO.setSearchValue(search);
		}
		if(serieDTO.getTotalPages()==0) {
			serieDTO.setTotalPages(1);
		}
		serieDTO.setNextPage(page);	
		ModelAndView mav = new ModelAndView("admin/serie/serieManage");
		mav.addObject("model", serieDTO);
		return mav;
	}
	
	@GetMapping("/admin/serie/save")
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/serie/formEdit_Save");
		mav.addObject("model", new SerieDTO());
		return mav;
	}
	
	@GetMapping("/admin/serie/edit")
	public ModelAndView editPage(
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/serie/formEdit_Save");
		SerieDTO serieDTO = serieSrv.findOne(id);
		mav.addObject("model", serieDTO);
		return mav;
	}
}

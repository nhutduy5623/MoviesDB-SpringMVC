package com.laptrinhweb.controller.web.work;

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

import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.repository.IRelatedPartyWorkDetailRepository;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IRelatedPartyDetailService;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.IRelatedPartyService;
import com.laptrinhweb.service.ISubGenreService;
import com.laptrinhweb.service.IWorkService;
import com.laptrinhweb.service.impl.RelatedPartyWorkDetailService;

@Controller(value = "workController")
public class workController {
	
	@Autowired
	IWorkService workService;
	
	@Autowired
	ISubGenreService subGenreService;
	
	@Autowired
	IGenreService genreService;
	
	@Autowired
	IRelatedPartyService relatedPartyService;
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;
	
	@Autowired
	IRelatedPartyDetailService relatedPartyDetailService;
	
	@RequestMapping(value = "/work", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "genreCode", required = false) String genreCode, 
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "subGenreCodeList", required = false) List<String> subGenreCodeList,
			@RequestParam(value = "subGenreCode", required = false) String subGenreCode
			
			) {
		if (page == null || limit == null) {
			page = 1;
			limit = 16;
		} 
		if (subGenreCode!=null) {
			if (subGenreCodeList==null)
				subGenreCodeList = new ArrayList<>();
			subGenreCodeList.add(subGenreCode);
		}
		Pageable pageable = new PageRequest(page-1, limit);
		WorkDTO workDTO = new WorkDTO();
		workDTO.setLimit(limit);
		workDTO.setSearchValue(search);
		if(genreCode!=null && genreCode.equals("-")) 
			genreCode = null;
//		if(genreCode.equals("-")) {
//			if(search == null) {
//				workDTO.setListResults(workService.findAll(pageable));
//				workDTO.setTotalPages(workService.countAll());
//				workDTO.setTotalPages((int) Math.ceil((double) workService.countAll()/workDTO.getLimit()));
//			} else {
//				workDTO.setSearchValue(search);
//				workDTO.setListResults(workService.findByNamePageable(search, pageable));
//				workDTO.setTotalPages((int) Math.ceil((double) workService.countByName(search)/workDTO.getLimit()));
//			}
//		} else {
//			workDTO.setListResults(workService.findByGenre_Code(genreCode, pageable));
//			workDTO.setTotalPages((int) Math.ceil((double) workService.countByGenre_Code(genreCode)/workDTO.getLimit()));			
//		}
		System.out.println(subGenreCodeList+"-"+genreCode+"-"+search);
		Map<String, Object> mapWorkDTO= workService.findBy_OrName_OrGenre_OrSubGenres(genreCode, subGenreCodeList, search, pageable);
		int size = (int) mapWorkDTO.get("size");
		System.out.println("size="+size);
		List<WorkDTO> listWorkDTO = (List<WorkDTO>) mapWorkDTO.get("list");
		workDTO.setListResults(listWorkDTO);
		workDTO.setTotalPages((int) Math.ceil((double) size/workDTO.getLimit()));
		if(workDTO.getTotalPages()==0) {
			workDTO.setTotalPages(1);
		}
		if(genreCode==null)
			genreCode="-";
		workDTO.setNextPage(page);		
		ModelAndView mav = new ModelAndView("web/work/gridWork");
		mav.addObject("model", workDTO);
		mav.addObject("genreCode", genreCode);
		mav.addObject("subGenreCodeList", subGenreCodeList);
		mav.addObject("subGenreList", subGenreService.findAll());
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		return mav;
	}
	
	
	@RequestMapping(value = "/work/detail", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "code", required = true) String code
			) {
		ModelAndView mav = new ModelAndView("web/work/workDetail");
		WorkDTO workDTO = workService.findOneByCode(code);	
		List<SubGenreDTO> subgenreList = subGenreService.findByWorkCode(code);
		GenreDTO genreDTO = genreService.findOneByCode(workDTO.getGenreCode());
		mav.addObject("model", workDTO);
		mav.addObject("subGenreList", subgenreList);
		mav.addObject("genre",genreDTO);
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		mav.addObject("relatedPartyDetailList", relatedPartyDetailService.findRPDetailByWork(workDTO));
		return mav;
	}
}

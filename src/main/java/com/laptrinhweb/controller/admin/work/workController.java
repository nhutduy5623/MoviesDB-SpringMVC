package com.laptrinhweb.controller.admin.work;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.SystemConstant;
import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.ISubGenreService;
import com.laptrinhweb.service.IWorkService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@Controller(value = "workController_admin")
public class workController {
	
	@Autowired
	IWorkService workService;
	
	@Autowired
	ISubGenreService subGenreService;
	
	@Autowired
	IGenreService genreService;

	@RequestMapping(value = "/admin/work", method = RequestMethod.GET)
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
		WorkDTO workDTO = new WorkDTO();
		workDTO.setLimit(limit);
		if(genreCode.equals("-")) {
			if(search == null) {
				workDTO.setListResults(workService.findAll(pageable));
				workDTO.setTotalPages(workService.countAll());
				workDTO.setTotalPages((int) Math.ceil((double) subGenreService.countAll()/workDTO.getLimit()));
			} else {
				workDTO.setSearchValue(search);
				workDTO.setListResults(workService.findByNamePageable(search, pageable));
				workDTO.setTotalPages((int) Math.ceil((double) subGenreService.countByName(search)/workDTO.getLimit()));
			}
		}else {
			workDTO.setListResults(workService.findByGenre_Code(genreCode, pageable));
			workDTO.setTotalPages((int) Math.ceil((double) workService.countByGenre_Code(genreCode)/workDTO.getLimit()));			
		}
		if(workDTO.getTotalPages()==0) {
			workDTO.setTotalPages(1);
		}
		workDTO.setNextPage(page);		
		ModelAndView mav = new ModelAndView("admin/work/workManage");
		mav.addObject("model", workDTO);
		mav.addObject("genreCode", genreCode);
		mav.addObject("genreList", genreService.findAll());
		return mav;
	}

	@RequestMapping(value = "/admin/work/save", method = RequestMethod.GET)
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/work/work_formEdit_Save");
		mav.addObject("model", new WorkDTO());
		mav.addObject("genreCodeList", genreService.findAll_HasMap());
		mav.addObject("subGenreCodeList", subGenreService.findAll_HasMap());
		return mav;
	}

	@RequestMapping(value = "/admin/work/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/work/work_formEdit_Save");
		WorkDTO workDTO = workService.findOne(id);
		mav.addObject("model", workDTO);
		mav.addObject("genreCodeList", genreService.findAll_HasMap());
		mav.addObject("subGenreCodeList", subGenreService.findAll_HasMap());
		return mav;
	}
	
	@RequestMapping(value = "/admin/work/fullfillinform", method = RequestMethod.GET)
	public ModelAndView fillInformWithAPI(@RequestParam(value = "code", required = true) String code, HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView("admin/work/work_formEdit_Save");
		WorkDTO workDTO = new WorkDTO();
		
		
		OkHttpClient client = new OkHttpClient();

		Request rqAPI = new Request.Builder()
		  .url("https://api.themoviedb.org/3/"+code+"?language=en-US")
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("Authorization", "Bearer "+SystemConstant.themoviedb_AccessToken)
		  .build();

		Response response = client.newCall(rqAPI).execute();
		
		
		System.out.println("response.body().string(): "+response.body().string());
		
		mav.addObject("model", workDTO);
		mav.addObject("genreCodeList", genreService.findAll_HasMap());
		mav.addObject("subGenreCodeList", subGenreService.findAll_HasMap());
		return mav;
	}
}

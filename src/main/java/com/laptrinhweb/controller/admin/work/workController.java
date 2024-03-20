package com.laptrinhweb.controller.admin.work;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhweb.SystemConstant;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.dto.TheMovieDB_Format.TMDB_TheFilm_Videos;
import com.laptrinhweb.dto.TheMovieDB_Format.TMDB_WorkDTO;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.IRelatedPartyService;
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

	@Autowired
	IRelatedPartyService relatedPartyService;

	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;

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
		Pageable pageable = new PageRequest(page - 1, limit);
		WorkDTO workDTO = new WorkDTO();
		workDTO.setLimit(limit);
		if (genreCode.equals("-")) {
			if (search == null) {
				workDTO.setListResults(workService.findAll(pageable));
				workDTO.setTotalPages(workService.countAll());
				workDTO.setTotalPages((int) Math.ceil((double) workService.countAll() / workDTO.getLimit()));
			} else {
				workDTO.setSearchValue(search);
				workDTO.setListResults(workService.findByNamePageable(search, pageable));
				workDTO.setTotalPages((int) Math.ceil((double) workService.countByName(search) / workDTO.getLimit()));
			}
		} else {
			workDTO.setListResults(workService.findByGenre_Code(genreCode, pageable));
			workDTO.setTotalPages(
					(int) Math.ceil((double) workService.countByGenre_Code(genreCode) / workDTO.getLimit()));
		}
		if (workDTO.getTotalPages() == 0) {
			workDTO.setTotalPages(1);
		}
		workDTO.setNextPage(page);
		ModelAndView mav = new ModelAndView("admin/work/workManage");
		mav.addObject("model", workDTO);
		mav.addObject("genreCode", genreCode);
		mav.addObject("listSubGenre", subGenreService.findAll_HasMap());
		mav.addObject("genreList", genreService.findAll());
		return mav;
	}

	@RequestMapping(value = "/admin/work/save", method = RequestMethod.GET)
	public ModelAndView savePage() {
		ModelAndView mav = new ModelAndView("admin/work/work_formEdit_Save");
		WorkDTO workDTO = new WorkDTO();
		mav.addObject("model", workDTO);
		mav.addObject("genreCodeList", genreService.findAll_HasMap());
		mav.addObject("subGenreCodeList", subGenreService.findAll_HasMap());
		mav.addObject("listRelatedPartyRole", relatedPartyRoleService.findAll());
		mav.addObject("listRelatedParty", relatedPartyService.findAll());
		mav.addObject("listRPByWork", relatedPartyService.findByWork(workDTO));
		mav.addObject("listRPWithoutWork", relatedPartyService.findWithoutWork(workDTO));
		return mav;
	}

	@RequestMapping(value = "/admin/work/edit", method = RequestMethod.GET)
	public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/work/work_formEdit_Save");
		WorkDTO workDTO = workService.findOne(id);
		mav.addObject("model", workDTO);
		mav.addObject("genreCodeList", genreService.findAll_HasMap());
		mav.addObject("subGenreCodeList", subGenreService.findAll_HasMap());
		mav.addObject("listRelatedPartyRole", relatedPartyRoleService.findAll());
		mav.addObject("listRelatedParty", relatedPartyService.findAll());
		mav.addObject("listRPByWork", relatedPartyService.findByWork(workDTO));
		mav.addObject("listRPWithoutWork", relatedPartyService.findWithoutWork(workDTO));
		return mav;
	}

	@RequestMapping(value = "/admin/work/fullfillinform", method = RequestMethod.GET)
	public ModelAndView fillInformWithAPI(@RequestParam(value = "code", required = true) String code, 
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request) throws IOException, ParseException {
		ModelAndView mav = new ModelAndView("admin/work/work_formEdit_Save");

		OkHttpClient client = new OkHttpClient();

		Request rqAPI = new Request.Builder().url("https://api.themoviedb.org/3/" + code + "?language=en-US").get()
				.addHeader("accept", "application/json")
				.addHeader("Authorization", "Bearer " + SystemConstant.themoviedb_AccessToken).build();

		Request rqVideo = new Request.Builder().url("https://api.themoviedb.org/3/" + code + "/videos?language=en-US")
				.get().addHeader("accept", "application/json")
				.addHeader("Authorization", "Bearer " + SystemConstant.themoviedb_AccessToken).build();

		Response response = client.newCall(rqAPI).execute();
		Response responseVideo = client.newCall(rqVideo).execute();
		String genreCode = code.split("/")[0];
		// JSON FLIM INFORMATION
		String jsonTMDB = response.body().string();
		System.out.println("response.body().string(): " + jsonTMDB);
		ObjectMapper objectMapper = new ObjectMapper();
		TMDB_WorkDTO workTMDB = objectMapper.readValue(jsonTMDB, TMDB_WorkDTO.class);
		WorkDTO workDTO = new WorkDTO(workTMDB, genreCode);

		// JSON Trailer
		String jsonTMDBVideo = responseVideo.body().string();
		TMDB_TheFilm_Videos videos = objectMapper.readValue(jsonTMDBVideo, TMDB_TheFilm_Videos.class);
		if(videos.getResults()!=null && !videos.getResults().isEmpty()) {
			workDTO.setVideo("https://www.youtube.com/watch?v="+videos.getResults().get(0).getKey());
		}
		if(id!=null) {
			workDTO.setId(id);
		}
		mav.addObject("model", workDTO);
		mav.addObject("genreCodeList", genreService.findAll_HasMap());
		mav.addObject("subGenreCodeList", subGenreService.findAll_HasMap());
		mav.addObject("listRelatedPartyRole", relatedPartyRoleService.findAll());
		mav.addObject("listRelatedParty", relatedPartyService.findAll());
		mav.addObject("listRPByWork", relatedPartyService.findByWork(new WorkDTO()));
		mav.addObject("listRPWithoutWork", relatedPartyService.findWithoutWork(new WorkDTO()));
		return mav;
	}
}

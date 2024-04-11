package com.laptrinhweb.controller.web.work;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhweb.dto.GenreDTO;
import com.laptrinhweb.dto.SubGenreDTO;
import com.laptrinhweb.dto.UserDTO;
import com.laptrinhweb.dto.ViewerVoteDetailDTO;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.dto.userSecurity;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IRelatedPartyDetailService;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.IRelatedPartyService;
import com.laptrinhweb.service.ISubGenreService;
import com.laptrinhweb.service.IUserFavoriteDetailService;
import com.laptrinhweb.service.IViewerVoteDetailService;
import com.laptrinhweb.service.IWorkService;
import com.laptrinhweb.util.SecurityUtil;

@Controller(value = "workController")
public class workController {

	@Autowired
	IWorkService workService;

	@Autowired
	IUserFavoriteDetailService userFavoriteDetailService;

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
	
	@Autowired
	IViewerVoteDetailService voteService;

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
		if (subGenreCode != null) {
			if (subGenreCodeList == null)
				subGenreCodeList = new ArrayList<>();
			subGenreCodeList.add(subGenreCode);
		}
		Pageable pageable = new PageRequest(page - 1, limit);
		WorkDTO workDTO = new WorkDTO();
		workDTO.setLimit(limit);
		workDTO.setSearchValue(search);
		if (genreCode != null && genreCode.equals("-"))
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
		System.out.println(subGenreCodeList + "-" + genreCode + "-" + search);
		Map<String, Object> mapWorkDTO = workService.findBy_OrName_OrGenre_OrSubGenres(genreCode, subGenreCodeList,
				search, pageable);
		int size = (int) mapWorkDTO.get("size");
		System.out.println("size=" + size);
		List<WorkDTO> listWorkDTO = (List<WorkDTO>) mapWorkDTO.get("list");
		workDTO.setListResults(listWorkDTO);
		workDTO.setTotalPages((int) Math.ceil((double) size / workDTO.getLimit()));
		if (workDTO.getTotalPages() == 0) {
			workDTO.setTotalPages(1);
		}
		if (genreCode == null)
			genreCode = "-";
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
			@RequestParam(value = "code", required = true) String code) {
		userSecurity currentUser = SecurityUtil.getPrincipal();
		int userFavorited = 0;
		if (currentUser != null) {
			System.out.println(currentUser.getCode());
			if (userFavoriteDetailService.checkUserFavorited(code, currentUser.getCode()))
				userFavorited = 1;
		}
		
		ViewerVoteDetailDTO voteDTO = new ViewerVoteDetailDTO();
		ViewerVoteDetailDTO voteOfUser = null;
		try {
			long userId = SecurityUtil.getPrincipal().getId();
			voteDTO.setListResults(voteService.findByWorkCodeNotUserPageable(code, userId, new PageRequest(0, 5)));
			voteDTO.setTotalPages((int) Math.ceil((double) (voteService.countByWorkCode(code)-1)/5));
			voteOfUser = voteService.findByWorkCodeAndUser(code, userId);
		}catch(Exception e) {
			voteDTO.setListResults(voteService.findByWorkCodePageable(code, new PageRequest(0, 5)));
			voteDTO.setTotalPages((int) Math.ceil((double) voteService.countByWorkCode(code)/5));
		}

		ModelAndView mav = new ModelAndView("web/work/workDetail");
		WorkDTO workDTO = workService.findOneByCode(code);
		List<SubGenreDTO> subgenreList = subGenreService.findByWorkCode(code);
		GenreDTO genreDTO = genreService.findOneByCode(workDTO.getGenreCode());
		mav.addObject("model", workDTO);
		mav.addObject("subGenreList", subgenreList);
		mav.addObject("userFavorited", userFavorited);
		mav.addObject("genre", genreDTO);
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		mav.addObject("relatedPartyDetailList", relatedPartyDetailService.findRPDetailByWork(workDTO));
	
		mav.addObject("vote", voteDTO);
		mav.addObject("voteOfUser", voteOfUser);
		
		return mav;
	}

	@RequestMapping(value = "/work/vote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
			+ "; charset=utf-8")
	@ResponseBody
	public String vote(@RequestBody ViewerVoteDetailDTO voteDTO) throws InterruptedException, IOException {
		Map<String, String> data = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		UserDTO user = new UserDTO();
		user.setId(SecurityUtil.getPrincipal().getId());
		voteDTO.setUser(user);
		voteDTO = voteService.save(voteDTO);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy 'at' HH:mm", Locale.US);
		data.put("userFullName", voteDTO.getUser().getFullName().trim());
		data.put("userAvatar", voteDTO.getUser().getAvatar());
		data.put("score", String.valueOf(voteDTO.getScore()));
		data.put("date", sdf.format(voteDTO.getModifiedDate()));
		data.put("content", voteDTO.getContent().trim());
		try {
			return objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/work/vote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			+ "; charset=utf-8")
	@ResponseBody
	public String getVote(@RequestParam(value = "code") String workCode, @RequestParam(value = "page") Integer page,
			@RequestParam(value = "limit") Integer limit) throws InterruptedException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy 'at' HH:mm", Locale.US);
		List<ViewerVoteDetailDTO> voteList = new ArrayList<ViewerVoteDetailDTO>();
		long totalPage = 1;
		try {
			long userId = SecurityUtil.getPrincipal().getId();
			voteList = voteService.findByWorkCodeNotUserPageable(workCode, userId, new PageRequest(page - 1, limit));
			totalPage = (long) Math.ceil((double) (voteService.countByWorkCode(workCode) - 1) / limit);
		} catch (Exception e) {
			voteList = voteService.findByWorkCodePageable(workCode, new PageRequest(page - 1, limit));
			totalPage = (long) Math.ceil((double) voteService.countByWorkCode(workCode) / limit);
		}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (ViewerVoteDetailDTO vote : voteList) {
			Map<String, String> dataItem = new HashMap<>();
			dataItem.put("userFullName", vote.getUser().getFullName().trim());
			dataItem.put("userAvatar", vote.getUser().getAvatar());
			dataItem.put("score", String.valueOf(vote.getScore()));
			dataItem.put("date", sdf.format(vote.getModifiedDate()));
			dataItem.put("content", vote.getContent().trim());
			result.add(dataItem);
		}
		Map<String, List<Map<String, String>>> data = new HashMap<>();
		data.put("results", result);
		try {
			return ("{\"totalPage\":" + totalPage + ",") + objectMapper.writeValueAsString(data).substring(1);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/work/vote", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE
			+ "; charset=utf-8")
	@ResponseBody
	public String deleteVote(@RequestParam(value = "code") String workCode) throws InterruptedException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Boolean> data = new HashMap<>();
		try {
			long userId = SecurityUtil.getPrincipal().getId();
			ViewerVoteDetailDTO vote = voteService.findByWorkCodeAndUser(workCode, userId);
			WorkDTO work = workService.findOneByCode(workCode);
			work.setVoteCount(work.getVoteCount() - 1);
			long newScore = 10;
			if(work.getVoteCount()==0) 
				work.setVoteCount(1L);
			else
				newScore = (work.getScore() * (work.getVoteCount() + 1) - vote.getScore()) / work.getVoteCount();
			work.setScore(Integer.valueOf(String.valueOf(newScore)));
			workService.save(work);
			voteService.delete(vote.getId());
			data.put("success", true);
			return objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
}

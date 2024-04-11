package com.laptrinhweb.controller.web.AI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhweb.SystemConstant;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.dto.AI.AiGeneratedDTO;
import com.laptrinhweb.dto.AI.ResulstGenerateDTO;
import com.laptrinhweb.service.IGenreService;
import com.laptrinhweb.service.IRelatedPartyRoleService;
import com.laptrinhweb.service.ISubGenreService;
import com.laptrinhweb.service.IWorkService;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

@Controller(value = "AiGeneratController")
public class AiGenerate {
	
	@Autowired
	IGenreService genreService;
	
	@Autowired
	IWorkService workService;
	
	@Autowired
	ISubGenreService subGenreService;
	
	@Autowired
	IRelatedPartyRoleService relatedPartyRoleService;
	
	
	@RequestMapping(value = "/AiGenerate", method = RequestMethod.GET)
	public ModelAndView homePage() throws JsonParseException, JsonMappingException, IOException {
		ModelAndView mav = new ModelAndView("web/AI/AiGenerate");		
		String answer = "Tôi có thể tìm kiếm thông tin liên quan đến các bộ phim giúp bạn!";
		List<WorkDTO> workDTOs = new ArrayList<WorkDTO>();		
        mav.addObject("answer", answer);        
        mav.addObject("listResults", workDTOs);
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AiGenerate", method = RequestMethod.POST)
	public ResulstGenerateDTO homePage(@RequestParam(value = "question", required = true) String question) throws JsonParseException, JsonMappingException, IOException {
		ModelAndView mav = new ModelAndView("web/AI/AiGenerate");
		
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(10, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);

        // Tạo JSON body cho yêu cầu POST
        String jsonBody = "{\"question\": \""+question+"\"}";

        // Địa chỉ của API Flask
        String apiUrl = SystemConstant.AI_API_URL;

        // Tạo yêu cầu POST
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        Request request = new Request.Builder()
            .url(apiUrl)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build();
        
        String responseBody = "\"answer\":, \"code\": []";
        try {
            // Thực hiện yêu cầu
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Lấy dữ liệu từ phản hồi
                responseBody = response.body().string();
                System.out.println("Response from Flask API: " + responseBody);
            } else {
            	responseBody = "Failed to call Flask API. Status code: ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("responseBody: "+ responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        AiGeneratedDTO generatedDTO = new AiGeneratedDTO();
        generatedDTO = objectMapper.readValue(responseBody, AiGeneratedDTO.class);
		
        List<WorkDTO> workDTOs = new ArrayList<WorkDTO>();
        for(String workCode:generatedDTO.getCode()) {
        	if(workCode!=null && !workCode.equals("")) {
            	workDTOs.add(workService.findOneByCode(workCode));
        	}
        }
        ResulstGenerateDTO results = new ResulstGenerateDTO();
        results.setAnswer(generatedDTO.getAnswer());
        results.setWorkDTOList(workDTOs);
        mav.addObject("answer", generatedDTO.getAnswer());
        mav.addObject("listResults", workDTOs);
		mav.addObject("genreList", genreService.findAll());
		mav.addObject("relatedPartyRoleList", relatedPartyRoleService.findAll());
		return results;
	}
}

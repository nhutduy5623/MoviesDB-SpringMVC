package com.laptrinhweb.dto.AI;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhweb.dto.WorkDTO;

public class ResulstGenerateDTO {
	private String answer;
	private List<WorkDTO> workDTOList = new ArrayList<>();
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public List<WorkDTO> getWorkDTOList() {
		return workDTOList;
	}
	public void setWorkDTOList(List<WorkDTO> workDTOList) {
		this.workDTOList = workDTOList;
	}	
}

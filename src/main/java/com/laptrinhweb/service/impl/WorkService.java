package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.dto.RelatedPartyWorkDetailDTO;
import com.laptrinhweb.dto.WorkDTO;
import com.laptrinhweb.entity.RelatedPartyWorkDetailEntity;
import com.laptrinhweb.entity.SubGenreEntity;
import com.laptrinhweb.entity.WorkEntity;
import com.laptrinhweb.repository.IRelatedPartyRepository;
import com.laptrinhweb.repository.IRelatedPartyWorkDetailRepository;
import com.laptrinhweb.repository.ISubGenreRepository;
import com.laptrinhweb.repository.IWorkRepository;
import com.laptrinhweb.service.IWorkService;
import com.laptrinhweb.service.impl.convertEntity_DTO.WorkConvert;

@Service
public class WorkService implements IWorkService{

	@Autowired
	IWorkRepository workRepository;
	
	@Autowired
	ISubGenreRepository subGenreRepository;
	
	@Autowired
	WorkConvert workConvert;	
	
	@Autowired
	IRelatedPartyWorkDetailRepository relatedPartyWorkDetailRepository;
	
	@Autowired
	IRelatedPartyRepository relatedPartyRepository;
	
	@Override
	@Transactional
	public WorkDTO save(WorkDTO workDTO) {
		if(workDTO.getId()!=null) {
			WorkEntity work = workRepository.findOne(workDTO.getId());
			for(RelatedPartyWorkDetailEntity RP_Work_detail: work.getRelatedPartyDetailList()) {
				relatedPartyWorkDetailRepository.delete(RP_Work_detail);
			}
		}
		WorkEntity workEntity = workRepository.save(workConvert.toEntity(workDTO));	
		workEntity.getRelatedPartyDetailList().clear();
		for(RelatedPartyWorkDetailDTO RB_Work_Detail: workDTO.getListRelatedPartyCode_Role()) {
			workEntity.getRelatedPartyDetailList().add(relatedPartyWorkDetailRepository.save(new RelatedPartyWorkDetailEntity(workEntity, relatedPartyRepository.findOneByCode(RB_Work_Detail.getRelatedPartyCode()), RB_Work_Detail.getRole())));
		}
		workDTO = workConvert.toDTO(workEntity);
		
		return workDTO;
	}

	@Override
	@Transactional
	public void delete(Long[] ids) {
		for(Long id:ids) {
			WorkEntity work = workRepository.findOne(id);
			work.getSubGenreList().clear();	
			for(RelatedPartyWorkDetailEntity RP_Work_detail: work.getRelatedPartyDetailList()) {
				relatedPartyWorkDetailRepository.delete(RP_Work_detail);
			}			
			work.getRelatedPartyDetailList().clear();
			workRepository.delete(id);
		}
	}

	@Override
	public List<WorkDTO> findAll() {
		List<WorkEntity> workEntities = workRepository.findAll();
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public List<WorkDTO> findAll(Pageable pageable) {
		List<WorkEntity> workEntities = workRepository.findAll(pageable).getContent();
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public WorkDTO findOne(Long id) {
		return workConvert.toDTO(workRepository.findOne(id));
	}
	
	@Override
	public WorkDTO findOneByCode(String code) {
		WorkEntity workEntity = workRepository.findOneByCode(code);
		if(workEntity!=null)
			return workConvert.toDTO(workEntity);
		return null;
	}

	@Override
	public Integer countAll() {
		// TODO Auto-generated method stub
		return (int) workRepository.count();
	}

	@Override
	public List<WorkDTO> findByNamePageable(String name, Pageable pageable) {
		List<WorkEntity> workEntities = workRepository.findByNamePageable(name, pageable).getContent();
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public Integer countByName(String name) {
		return workRepository.countByName(name);
	}
	

	@Override
	public Map<String, String> findAll_HasMap() {
		Map<String, String> workCodeList = new HashMap<>();
		List<WorkEntity> listWorkEntity = workRepository.findAll();
		for(WorkEntity workEntity: listWorkEntity) {
			workCodeList.put(workEntity.getCode(), workEntity.getName());
		}
		return workCodeList;
	}

	@Override
	public List<WorkDTO> findByGenre_Code(String genreCode, Pageable pageable) {
		List<WorkEntity> workEntities = workRepository.findByGenre_Code(genreCode, pageable).getContent();
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public Integer countByGenre_Code(String genreCode) {
		return workRepository.countByGenre_Code(genreCode);
	}

//	@Override
//	public Map<String, Object> find_CountByGenreCodeAndSubgenreCodes(String genreCode, List<String> subgenreCodeList,
//			Pageable pageable) {
//		List<WorkEntity> workEntities = workRepository.findByGenreCodeAndSubgenreCodes(genreCode, subgenreCodeList, pageable).getContent();
//		List<WorkDTO> workDTOs = new ArrayList<>();
//		
//		for(WorkEntity workEntity : workEntities) {
//			workDTOs.add(workConvert.toDTO(workEntity));
//		}		
//		int size = (int) workRepository.countByGenreCodeAndSubgenreCodes(genreCode, subgenreCodeList);
//		Map<String, Object> mapWorkDTO = new HashMap<>();
//		mapWorkDTO.put("list", workDTOs);
//		mapWorkDTO.put("size", size);
//		return mapWorkDTO;
//	}

	@Override
	public Map<String, Object> findBy_OrName_OrGenre_OrSubGenres(String genreCode, List<String> subgenreCodeList,
			String searchValue, Pageable pageable) {
		List<WorkEntity> workEntities = new ArrayList<>();
		int size = 0;
		if(subgenreCodeList==null||subgenreCodeList.isEmpty()) {
			workEntities = workRepository.findByGenreCodeAndNameLike(searchValue, genreCode, pageable).getContent();
			size = (int) workRepository.countByGenreCodeAndNameLike(searchValue, genreCode);
		} else {
			workEntities = workRepository.findByGenreCodeAndSubgenreCodesAndNameLike(searchValue, genreCode, subgenreCodeList, pageable).getContent();			
			size = (int) workRepository.countByGenreCodeAndSubgenreCodesAndNameLike(searchValue, genreCode, subgenreCodeList);
		}
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}		
		System.out.println("Size="+size);
		Map<String, Object> mapWorkDTO = new HashMap<>();
		mapWorkDTO.put("list", workDTOs);
		mapWorkDTO.put("size", size);
		return mapWorkDTO;
	}
	
	@Override
	public List<WorkDTO> findByRelatedPartyCode(String code) {
		List<WorkEntity> workEntities = workRepository.findByRelatedPartyCode(code);
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public List<WorkDTO> findTopByScore(int limit) {
		Pageable pageable = new  PageRequest(1, limit);
		List<WorkEntity> workEntities = workRepository.findTopByScore(pageable);
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public List<WorkDTO> findTopByVoteCount(int limit) {
		Pageable pageable = new  PageRequest(1, limit);
		List<WorkEntity> workEntities = workRepository.findTopByVoteCount(pageable);
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public List<WorkDTO> findTopByRelatedDate(int limit) {
		Pageable pageable = new  PageRequest(1, limit);
		List<WorkEntity> workEntities = workRepository.findTopByRelatedDate(pageable);
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

	@Override
	public List<WorkDTO> findBySerieCode(String serieCode) {
		List<WorkEntity> workEntities = workRepository.findBySerieCode(serieCode);
		List<WorkDTO> workDTOs = new ArrayList<>();
		for(WorkEntity workEntity : workEntities) {
			workDTOs.add(workConvert.toDTO(workEntity));
		}
		return workDTOs;
	}

}

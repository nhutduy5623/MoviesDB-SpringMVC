package com.laptrinhweb.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhweb.dto.TheMovieDB_Format.TMDB_WorkDTO;
import com.laptrinhweb.dto.TheMovieDB_Format.TMDB_subGenreDTO;

public class WorkDTO extends AbstractDTO<WorkDTO>{
	private String code;
	private String name;
	private String overview;
	private String thumbnail;
	private String video;
	private double budget;
	private Long followerCount=(long) 0;
	private Long viewerCount=(long) 0;
	private int score;
	private Long voteCount;
	private Date relatedDate;
	private int status;
	private String background;
	private String genreCode;
	private String serieCode;
	private List<String> subGenreCodeList = new ArrayList<>();
	private List<RelatedPartyWorkDetailDTO> listRelatedPartyCode_Role = new ArrayList<>();
	//Cần thêm Related
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}	
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Long getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}
	public Long getViewerCount() {
		return viewerCount;
	}
	public void setViewerCount(Long viewerCount) {
		this.viewerCount = viewerCount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Long getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}
	public Date getRelatedDate() {
		return relatedDate;
	}
	public void setRelatedDate(Date relatedDate) {
		this.relatedDate = relatedDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getGenreCode() {
		return genreCode;
	}
	public void setGenreCode(String genreCode) {
		this.genreCode = genreCode;
	}
	public String getSerieCode() {
		return serieCode;
	}
	public void setSerieCode(String serieCode) {
		this.serieCode = serieCode;
	}
	public List<String> getSubGenreCodeList() {
		return subGenreCodeList;
	}
	public void setSubGenreCodeList(List<String> subGenreCodeList) {
		this.subGenreCodeList = subGenreCodeList;
	}
	public List<RelatedPartyWorkDetailDTO> getListRelatedPartyCode_Role() {
		return listRelatedPartyCode_Role;
	}
	public void setListRelatedPartyCode_Role(List<RelatedPartyWorkDetailDTO> listRelatedPartyCode_Role) {
		this.listRelatedPartyCode_Role = listRelatedPartyCode_Role;
	}
	public WorkDTO(String code, String name, String overview, String thumbnail, double budget, Long followerCount,
			Long viewerCount, int score, Long voteCount, Date relatedDate, int status, String background,
			String genreCode, String serieCode, List<String> subGenreCodeList,
			List<RelatedPartyWorkDetailDTO> listRelatedPartyCode_Role) {
		super();
		this.code = code;
		this.name = name;
		this.overview = overview;
		this.thumbnail = thumbnail;
		this.budget = budget;
		this.followerCount = followerCount;
		this.viewerCount = viewerCount;
		this.score = score;
		this.voteCount = voteCount;
		this.relatedDate = relatedDate;
		this.status = status;
		this.background = background;
		this.genreCode = genreCode;
		this.serieCode = serieCode;
		this.subGenreCodeList = subGenreCodeList;
		this.listRelatedPartyCode_Role = listRelatedPartyCode_Role;
	}	
	
	 public WorkDTO(TMDB_WorkDTO workTMDB, String genreCode) throws java.text.ParseException {
	        this.code = workTMDB.getId();
	        if(genreCode.equals("movie")) {
		        this.name = workTMDB.getTitle();
	        } else {
		        this.name = workTMDB.getName();
	        }
	        this.overview = workTMDB.getOverview();
	        this.thumbnail = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2"+workTMDB.getPoster_path();
	        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
	        System.out.println(workTMDB.getFirst_air_date());
	        if(workTMDB.getFirst_air_date()!=null) {
	        	System.out.println(workTMDB.getFirst_air_date());
	        	this.relatedDate = new Date(formatter.parse(workTMDB.getFirst_air_date()).getTime());
	        	System.out.println(this.relatedDate);

	        }
	        this.background = "https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces"+workTMDB.getBackdrop_path();
	        this.voteCount = Long.parseLong(workTMDB.getVote_count());
	        this.score = (int) Double.parseDouble(workTMDB.getVote_average());
	        this.genreCode = genreCode;
	        if(workTMDB.getBudget()!=null)
	        	this.budget = Double.parseDouble(workTMDB.getBudget());
	        for(TMDB_subGenreDTO tmdb_subGenreDTO:workTMDB.getGenres()) {
		        this.subGenreCodeList.add(tmdb_subGenreDTO.getId());
	        }
	        // Other fields can be similarly handled
	 }
	public WorkDTO() {
	}
	@Override
	public String toString() {
		return "WorkDTO [code=" + code + ", name=" + name + ", overview=" + overview + ", thumbnail=" + thumbnail
				+ ", budget=" + budget + ", followerCount=" + followerCount + ", viewerCount=" + viewerCount
				+ ", score=" + score + ", voteCount=" + voteCount + ", relatedDate=" + relatedDate + ", status="
				+ status + ", background=" + background + ", genreCode=" + genreCode + ", serieCode=" + serieCode
				+ ", subGenreCodeList=" + subGenreCodeList + ", listRelatedPartyCode_Role=" + listRelatedPartyCode_Role
				+ "]";
	}
	 
}

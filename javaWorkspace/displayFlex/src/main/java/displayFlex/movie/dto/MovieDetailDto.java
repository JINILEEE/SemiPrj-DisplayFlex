package displayFlex.movie.dto;

import java.util.List;

import displayFlex.movie.vo.StillImageVo;

public class MovieDetailDto {
	private String movieNo; 					//영화 번호
	private String movieName;					//영화 이름
	private String gradeName;					//관람 등급
	private String actors;							//배우
	private String story;							//줄거리
	private String rate;							//평점
	private String mainDirector;				//감독
	private String movieImage;					//포스터
	private String  runningTime;				//상영 시간
	private String releaseDate;					//개봉일자
	private boolean isScreening;				//상영 여부
	private String genre;							//장르
	private String nation;						//제작국가
	private List<StillImageVo> stillsList;		//스틸 이미지

	public MovieDetailDto(String movieNo, String movieName, String gradeName, String actors, String story, String rate,
			String mainDirector, String movieImage, String runningTime, String releaseDate, String genre,
			String nation) {
		super();
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.gradeName = gradeName;
		this.actors = actors;
		this.story = story;
		this.rate = rate;
		this.mainDirector = mainDirector;
		this.movieImage = movieImage;
		this.runningTime = runningTime;
		this.releaseDate = releaseDate;
		this.isScreening = false;
		this.genre = genre;
		this.nation = nation;
	}

	public boolean getIsScreening() {
		return isScreening;
	}

	public void setIsScreening(boolean isScreening) {
		this.isScreening = isScreening;
	}

	public String getGradeName() {
		return gradeName;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public List<StillImageVo> getStillsList() {
		return stillsList;
	}

	public void setStillsList(List<StillImageVo> stillsList) {
		this.stillsList = stillsList;
	}

	public String getMovieNo() {
		return movieNo;
	}

	public String getMovieName() {
		return movieName;
	}

	public String getActors() {
		return actors;
	}

	public String getStory() {
		return story;
	}

	public String getRate() {
		return rate;
	}

	public String getMainDirector() {
		return mainDirector;
	}

	public String getMovieImage() {
		return movieImage;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public String getGenre() {
		return genre;
	}

	public String getNation() {
		return nation;
	}

	@Override
	public String toString() {
		return "MovieDetailDto [movieNo=" + movieNo + ", movieName=" + movieName + ", gradeName=" + gradeName
				+ ", actors=" + actors + ", story=" + story + ", rate=" + rate + ", mainDirector=" + mainDirector
				+ ", movieImage=" + movieImage + ", runningTime=" + runningTime + ", releaseDate=" + releaseDate
				+ ", isScreening=" + isScreening + ", genre=" + genre + ", nation=" + nation + ", stillsList="
				+ stillsList + "]";
	}
}

package displayFlex.movie.vo;

public class MovieVo {

	private String movieNo; 			//영화 번호
	private String movieName;			//영화 이름
	private String actors;					//배우
	private String story;					//줄거리
	private String rate;					//평점
	private String mainDirector;		//감독
	private String screenGradeNo;		//등급번호
	private String movieImage;			//포스터
	private String  runningTime;		//상영 시간
	private String releaseDate;			//개봉일
	private String writeDate;			//작성일
	private String modifyDate;			//수정일
	private String genre;					//장르
	private String nation;				//제작국가
	private String mainImage;			//메인 화면용 이미지
	
	public MovieVo(String movieNo, String movieName, String releaseDate) {
		super();
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.releaseDate = releaseDate;
	}

	public MovieVo(String movieNo, String movieName, String actors, String story, String rate, String mainDirector,
			String screenGradeNo, String movieImage, String runningTime, String releaseDate, String writeDate,
			String modifyDate, String genre, String nation, String mainImage) {
		super();
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.actors = actors;
		this.story = story;
		this.rate = rate;
		this.mainDirector = mainDirector;
		this.screenGradeNo = screenGradeNo;
		this.movieImage = movieImage;
		this.runningTime = runningTime;
		this.releaseDate = releaseDate;
		this.writeDate = writeDate;
		this.modifyDate = modifyDate;
		this.genre = genre;
		this.nation = nation;
		this.mainImage = mainImage;
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

	public String getScreenGradeNo() {
		return screenGradeNo;
	}

	public String getMovieImage() {
		return movieImage;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public String getGenre() {
		return genre;
	}

	public String getNation() {
		return nation;
	}

	public String getMainImage() {
		return mainImage;
	}

	@Override
	public String toString() {
		return "MovieVo [movieNo=" + movieNo + ", movieName=" + movieName + ", actors=" + actors + ", story=" + story
				+ ", rate=" + rate + ", mainDirector=" + mainDirector + ", screenGradeNo=" + screenGradeNo
				+ ", movieImage=" + movieImage + ", runningTime=" + runningTime + ", releaseDate=" + releaseDate
				+ ", writeDate=" + writeDate + ", modifyDate=" + modifyDate + ", genre=" + genre + ", nation=" + nation
				+ ", mainImage=" + mainImage + "]";
	}
	
}

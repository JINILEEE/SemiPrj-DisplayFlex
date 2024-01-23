package displayFlex.movie.dto;

/**
 * 영화 목록용 데이터 자료형
 */
public class MovieListDto {
	private String movieNo; 				//영화 번호
	private String movieName;				//영화 이름
	private String screenGradeName;		//등급이름
	private String movieImage;				//포스터
	private String releaseDate;				//개봉일
	private String  runningTime;			//상영 시간
	private String rate;						//평점
	private String genre;						//장르
	
	public MovieListDto(String movieNo, String movieName, String screenGradeName, String movieImage, String releaseDate,
			String runningTime, String rate, String genre) {
		super();
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.screenGradeName = screenGradeName;
		this.movieImage = movieImage;
		this.releaseDate = releaseDate;
		this.runningTime = runningTime;
		this.rate = rate;
		this.genre = genre;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getMovieNo() {
		return movieNo;
	}

	public String getMovieName() {
		return movieName;
	}

	public String getScreenGradeName() {
		return screenGradeName;
	}

	public String getMovieImage() {
		return movieImage;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public String getRate() {
		return rate;
	}

	public String getGenre() {
		return genre;
	}

	@Override
	public String toString() {
		return "MovieListDto [movieNo=" + movieNo + ", movieName=" + movieName + ", screenGradeName=" + screenGradeName
				+ ", movieImage=" + movieImage + ", releaseDate=" + releaseDate + ", runningTime=" + runningTime
				+ ", rate=" + rate + ", genre=" + genre + "]";
	}
	
	
}

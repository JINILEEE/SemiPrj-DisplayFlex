package displayFlex.movie.vo;

public class MovieCateVo {
	private String movieCateNo;
	private String movieNo;
	private String genreCateNo;
	
	public MovieCateVo(String movieNo, String genreCateNo) {
		super();
		this.movieNo = movieNo;
		this.genreCateNo = genreCateNo;
	}

	public String getMovieCateNo() {
		return movieCateNo;
	}

	public String getMovieNo() {
		return movieNo;
	}

	public String getGenreCateNo() {
		return genreCateNo;
	}

	@Override
	public String toString() {
		return "MovieCateVo [movieCateNo=" + movieCateNo + ", movieNo=" + movieNo + ", genreCateNo=" + genreCateNo
				+ "]";
	}
	
	
}

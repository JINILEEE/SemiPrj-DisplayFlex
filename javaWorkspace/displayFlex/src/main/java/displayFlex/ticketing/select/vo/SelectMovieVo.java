package displayFlex.ticketing.select.vo;

import java.util.List;

// 예매 선택창 영화 리스트(임시)
public class SelectMovieVo {
	
	private String movieNo;
	private String movieName;
	private String movieImage;
	private String screenGradeNo;
	
	public String getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(String movieNo) {
		this.movieNo = movieNo;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieImage() {
		return movieImage;
	}
	public void setMovieImage(String movieImage) {
		this.movieImage = movieImage;
	}
	public String getScreenGradeNo() {
		return screenGradeNo;
	}
	public void setScreenGradeNo(String screenGradeNo) {
		this.screenGradeNo = screenGradeNo;
	}
	public SelectMovieVo(String movieNo, String movieName, String movieImage, String screenGradeNo) {
		super();
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.movieImage = movieImage;
		this.screenGradeNo = screenGradeNo;
	}
	public SelectMovieVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SelectMovieVo [movieNo=" + movieNo + ", movieName=" + movieName + ", movieImage=" + movieImage
				+ ", screenGradeNo=" + screenGradeNo + "]";
	}

	
	
	
}

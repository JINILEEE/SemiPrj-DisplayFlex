package displayFlex.screeninginfo.vo;

public class ScreeingInfoVo {
	private String screeningInfoNo;
	private String movieNo;
	private String theaterNo;
	private String startDate;
	
	public ScreeingInfoVo(String movieNo, String theaterNo, String startDate) {
		super();
		this.movieNo = movieNo;
		this.theaterNo = theaterNo;
		this.startDate = startDate;
	}

	public String getScreeningInfoNo() {
		return screeningInfoNo;
	}

	public String getMovieNo() {
		return movieNo;
	}

	public String getTheaterNo() {
		return theaterNo;
	}

	public String getStartDate() {
		return startDate;
	}

	@Override
	public String toString() {
		return "ScreeingInfoVo [screeningInfoNo=" + screeningInfoNo + ", movieNo=" + movieNo + ", theaterNo="
				+ theaterNo + ", startDate=" + startDate + "]";
	}
	
}

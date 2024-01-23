package displayFlex.ticketing.select.vo;

public class ScreeningDateVo {

	private String movieNo;
	private String screeningInfoNo;	
	private String theaterNo;
	private String screeningTimeNo;
	private String startTime;
	
	public String getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(String movieNo) {
		this.movieNo = movieNo;
	}
	public String getScreeningInfoNo() {
		return screeningInfoNo;
	}
	public void setScreeningInfoNo(String screeningInfoNo) {
		this.screeningInfoNo = screeningInfoNo;
	}
	public String getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(String theaterNo) {
		this.theaterNo = theaterNo;
	}
	public String getScreeningTimeNo() {
		return screeningTimeNo;
	}
	public void setScreeningTimeNo(String screeningTimeNo) {
		this.screeningTimeNo = screeningTimeNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public ScreeningDateVo(String movieNo, String screeningInfoNo, String theaterNo, String screeningTimeNo,
			String startTime) {
		super();
		this.movieNo = movieNo;
		this.screeningInfoNo = screeningInfoNo;
		this.theaterNo = theaterNo;
		this.screeningTimeNo = screeningTimeNo;
		this.startTime = startTime;
	}

	
	
	public ScreeningDateVo(String startTime, String theaterNo, String screeningTimeNo) {
		super();
		this.theaterNo = theaterNo;
		this.startTime = startTime;
		this.screeningTimeNo = screeningTimeNo;
	}
	public ScreeningDateVo(String startTime) {
		super();
		this.startTime = startTime;
	}
	public ScreeningDateVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ScreeningDateVo [movieNo=" + movieNo + ", screeningInfoNo=" + screeningInfoNo + ", theaterNo="
				+ theaterNo + ", screeningTimeNo=" + screeningTimeNo + ", startTime=" + startTime + "]";
	}
	
	
	
	
}
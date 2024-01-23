package displayFlex.screeninginfo.dto;

public class ScreenInfoDto {
	private String screeningTimeNo;
	private String title;
	private String theater;
	private String date;
	private String startTime;
	private String endTime;
	
	public ScreenInfoDto(String screeningTimeNo, String title, String theater, String date, String startTime,
			String endTime) {
		super();
		this.screeningTimeNo = screeningTimeNo;
		this.title = title;
		this.theater = theater;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public ScreenInfoDto(String title, String theater, String date, String startTime, String endTime) {
		super();
		this.title = title;
		this.theater = theater;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getScreeningTimeNo() {
		return screeningTimeNo;
	}

	public String getTitle() {
		return title;
	}

	public String getTheater() {
		return theater;
	}

	public String getDate() {
		return date;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	@Override
	public String toString() {
		return "ScreenInfoDto [screeningTimeNo=" + screeningTimeNo + ", title=" + title + ", theater=" + theater
				+ ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}

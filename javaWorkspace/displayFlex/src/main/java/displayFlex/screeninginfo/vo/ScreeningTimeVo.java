package displayFlex.screeninginfo.vo;

public class ScreeningTimeVo {
	private String screeningTimeNo;		//상영 시간 번호
	private String screeningInfoNo;		//상영 정보 번호
	private String	StartTime;				//시작 시간
	private String endTime;					//종료 시간
	
	public ScreeningTimeVo(String screeningInfoNo, String startTime, String endTime) {
		super();
		this.screeningInfoNo = screeningInfoNo;
		StartTime = startTime;
		this.endTime = endTime;
	}

	public String getScreeningInfoNo() {
		return screeningInfoNo;
	}

	public void setScreeningInfoNo(String screeningInfoNo) {
		this.screeningInfoNo = screeningInfoNo;
	}

	public String getScreeningTimeNo() {
		return screeningTimeNo;
	}

	public String getStartTime() {
		return StartTime;
	}

	public String getEndTime() {
		return endTime;
	}

	@Override
	public String toString() {
		return "ScreeningTime [screeningTimeNo=" + screeningTimeNo + ", screeningInfoNo=" + screeningInfoNo
				+ ", StartTime=" + StartTime + ", endTime=" + endTime + "]";
	}
	
}

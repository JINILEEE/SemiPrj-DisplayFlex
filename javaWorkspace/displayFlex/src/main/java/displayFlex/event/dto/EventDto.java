package displayFlex.event.dto;

public class EventDto {
	
	private String eventNo;
	private String eventtypeNo;
	private String memberNo;
	private String eventTitle;
	private String eventContents;
	private String eventPreparationdate;
	private String eventReportingdate;
	private String eventProgress;
	private String eventStartdate;
	private String eventEnddate;
	private String eventHit;
	
	public String getEventNo() {
		return eventNo;
	}
	public void setEventNo(String eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventtypeNo() {
		return eventtypeNo;
	}
	public void setEventtypeNo(String eventtypeNo) {
		this.eventtypeNo = eventtypeNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventContents() {
		return eventContents;
	}
	public void setEventContents(String eventContents) {
		this.eventContents = eventContents;
	}
	public String getEventPreparationdate() {
		return eventPreparationdate;
	}
	public void setEventPreparationdate(String eventPreparationdate) {
		this.eventPreparationdate = eventPreparationdate;
	}
	public String getEventReportingdate() {
		return eventReportingdate;
	}
	public void setEventReportingdate(String eventReportingdate) {
		this.eventReportingdate = eventReportingdate;
	}
	public String getEventProgress() {
		return eventProgress;
	}
	public void setEventProgress(String eventProgress) {
		this.eventProgress = eventProgress;
	}
	public String getEventStartdate() {
		return eventStartdate;
	}
	public void setEventStartdate(String eventStartdate) {
		this.eventStartdate = eventStartdate;
	}
	public String getEventEnddate() {
		return eventEnddate;
	}
	public void setEventEnddate(String eventEnddate) {
		this.eventEnddate = eventEnddate;
	}
	public String getEventHit() {
		return eventHit;
	}
	public void setEventHit(String eventHit) {
		this.eventHit = eventHit;
	}
	public EventDto(String eventNo, String eventtypeNo, String memberNo, String eventTitle, String eventContents,
			String eventPreparationdate, String eventReportingdate, String eventProgress, String eventStartdate,
			String eventEnddate, String eventHit) {
		super();
		this.eventNo = eventNo;
		this.eventtypeNo = eventtypeNo;
		this.memberNo = memberNo;
		this.eventTitle = eventTitle;
		this.eventContents = eventContents;
		this.eventPreparationdate = eventPreparationdate;
		this.eventReportingdate = eventReportingdate;
		this.eventProgress = eventProgress;
		this.eventStartdate = eventStartdate;
		this.eventEnddate = eventEnddate;
		this.eventHit = eventHit;
	}
	public EventDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EventDto [eventNo=" + eventNo + ", eventtypeNo=" + eventtypeNo + ", memberNo=" + memberNo
				+ ", eventTitle=" + eventTitle + ", eventContents=" + eventContents + ", eventPreparationdate="
				+ eventPreparationdate + ", eventReportingdate=" + eventReportingdate + ", eventProgress="
				+ eventProgress + ", eventStartdate=" + eventStartdate + ", eventEnddate=" + eventEnddate
				+ ", eventHit=" + eventHit + "]";
	}
	
}

package displayFlex.serviceCenter.notice.vo;

public class NoticeVo {
	
	private String noticeNo;
	private String title;
	private String content;
	private String enrollDate;
	private String modifyDate;
	private String deleteYn;
	private String hit;
	
	public NoticeVo(String noticeNo, String title, String content, String enrollDate, String modifyDate,
			String deleteYn, String hit) {
		super();
		this.noticeNo = noticeNo;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteYn = deleteYn;
		this.hit = hit;
	}

	/**
	 * 
	 */
	public NoticeVo() {
		// TODO Auto-generated constructor stub
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "NoticeVo [noticeNo=" + noticeNo + ", title=" + title + ", content=" + content + ", enrollDate="
				+ enrollDate + ", modifyDate=" + modifyDate + ", deleteYn=" + deleteYn + ", hit=" + hit + "]";
	}
	
	
	
	

}

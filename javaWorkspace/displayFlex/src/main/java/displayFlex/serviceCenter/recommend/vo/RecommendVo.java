package displayFlex.serviceCenter.recommend.vo;

public class RecommendVo {
	
	private String recommendMvNo;
	private String memberNo;
	private String writerNick;
	private String year;
	private String title;
	private String content;
	private String enrollDate;
	private String modifyDate;
	private String deleteYn;
	private String recommendCount;
	private String hit;
	
	/**
	 * 
	 */
	public RecommendVo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param recommendMvNo
	 * @param memberNo
	 * @param writerNick
	 * @param year
	 * @param title
	 * @param content
	 * @param enrollDate
	 * @param modifyDate
	 * @param deleteYn
	 * @param recommendCount
	 * @param hit
	 */
	public RecommendVo(String recommendMvNo, String memberNo, String writerNick, String year, String title,
			String content, String enrollDate, String modifyDate, String deleteYn, String recommendCount, String hit) {
		super();
		this.recommendMvNo = recommendMvNo;
		this.memberNo = memberNo;
		this.writerNick = writerNick;
		this.year = year;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteYn = deleteYn;
		this.recommendCount = recommendCount;
		this.hit = hit;
	}

	public String getRecommendMvNo() {
		return recommendMvNo;
	}

	public void setRecommendMvNo(String recommendMvNo) {
		this.recommendMvNo = recommendMvNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getWriterNick() {
		return writerNick;
	}

	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(String recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "RecommendVo [recommendMvNo=" + recommendMvNo + ", memberNo=" + memberNo + ", writerNick=" + writerNick
				+ ", year=" + year + ", title=" + title + ", content=" + content + ", enrollDate=" + enrollDate
				+ ", modifyDate=" + modifyDate + ", deleteYn=" + deleteYn + ", recommendCount=" + recommendCount
				+ ", hit=" + hit + "]";
	}
	
	
}

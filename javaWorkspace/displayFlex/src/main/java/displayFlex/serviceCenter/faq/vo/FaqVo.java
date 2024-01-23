package displayFlex.serviceCenter.faq.vo;

public class FaqVo {
	
	private String faqNo;
	private String faqCategoryNo;
	private String title;
	private String content;
	private String hit;
	private String enrollDate;
	private String modifyDate;
	private String deleteYn;
	private String categoryName;
	
	public FaqVo(String faqNo, String faqCategoryNo, String title, String content, String hit, String enrollDate,
			String modifyDate, String deleteYn, String categoryName) {
		super();
		this.faqNo = faqNo;
		this.faqCategoryNo = faqCategoryNo;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteYn = deleteYn;
		this.categoryName = categoryName;
	}

	public FaqVo() {
		// TODO Auto-generated constructor stub
	}

	public String getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(String faqNo) {
		this.faqNo = faqNo;
	}

	public String getFaqCategoryNo() {
		return faqCategoryNo;
	}

	public void setFaqCategoryNo(String faqCategoryNo) {
		this.faqCategoryNo = faqCategoryNo;
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

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "FaqVo [faqNo=" + faqNo + ", faqCategoryNo=" + faqCategoryNo + ", title=" + title + ", content="
				+ content + ", hit=" + hit + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", deleteYn="
				+ deleteYn + ", categoryName=" + categoryName + "]";
	}
	
	
	
	
	
	
	
}

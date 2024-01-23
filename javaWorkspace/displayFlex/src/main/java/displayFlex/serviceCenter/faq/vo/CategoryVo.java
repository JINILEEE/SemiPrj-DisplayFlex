package displayFlex.serviceCenter.faq.vo;

public class CategoryVo {
	
	private String faqCategoryNo;
	private String categoryName;
	
	public CategoryVo(String faqCategoryNo, String categoryName) {
		super();
		this.faqCategoryNo = faqCategoryNo;
		this.categoryName = categoryName;
	}

	public CategoryVo() {
		// TODO Auto-generated constructor stub
	}

	public String getFaqCategoryNo() {
		return faqCategoryNo;
	}

	public void setFaqCategoryNo(String faqCategoryNo) {
		this.faqCategoryNo = faqCategoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryVo [faqCategoryNo=" + faqCategoryNo + ", categoryName=" + categoryName + "]";
	}
	
	
	
	
	

}

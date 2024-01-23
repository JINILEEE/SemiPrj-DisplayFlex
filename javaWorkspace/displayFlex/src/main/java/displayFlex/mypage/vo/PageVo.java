package displayFlex.mypage.vo;

public class PageVo {

	private int listCount;		//총 게시글 개수
	private int currentPage;	//현재 페이지
	private int pageLimit;		//페이징 영역 페이지갯수
	private int allLimit;		//전체 게시글 갯수
	
	private int maxPage;		//가장 마지막 페이지
	private int startPage;		//페이징 영역 시작값
	private int endPage;		//페이징 영역 마지막값
	
	private int startRow;		//조회할 첫번째 행 번호(ROWNUM)
	private int lastRow;		//조회할 마지막 행 번호(ROWNUM)
	private int startDateFilter;
	private int DateFilter;
    public int getDateFilter() {
		return DateFilter;
	}
	public void setDateFilter(int dateFilter) {
		DateFilter = dateFilter;
	}

	private String startDate;  // 추가
    private String endDate;    // 추가

	
	public PageVo(int listCount , int currentPage, int pageLimit , int allLimit) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.allLimit = allLimit;
		
		this.maxPage = (int) Math.ceil((double)listCount/allLimit);
	      this.startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
	      this.endPage = startPage + pageLimit - 1;
	      
	      if(endPage > maxPage) {
	         endPage = maxPage;
	      }
	      
	      this.startRow = (currentPage - 1) * allLimit + 1;
	      this.lastRow = startRow + allLimit - 1;
	}
	

	public int getStartRow() {
		return startRow;
	}


	public int getLastRow() {
		return lastRow;
	}


	public int getListCount() {
		return listCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public int getAllLimit() {
		return allLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	public int getStartDateFilter() {
        return startDateFilter;
    }
	
	 public void setStartDateFilter(int startDateFilter) {
	        this.startDateFilter = startDateFilter;
	    }
	 
	 // 추가된 메서드
	    public void setStartDate(String startDate) {
	        this.startDate = startDate;
	    }

	    public void setEndDate(String endDate) {
	        this.endDate = endDate;
	    }

	    // 추가된 메서드
	    public String getStartDate() {
	        return startDate;
	    }

	    public String getEndDate() {
	        return endDate;
	    }
	    

}

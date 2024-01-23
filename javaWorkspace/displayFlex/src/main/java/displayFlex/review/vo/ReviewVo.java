package displayFlex.review.vo;

public class ReviewVo {
	private String reviewNo;			//리뷰번호

	private String memberNo;			//회원번호
	private String movieNo;				//영화번호
	private String content;				//내용
	private String writeDate;			//작성일자
	private String rate;					//평점
	private String delYn; 				//삭제 여부
	
	public ReviewVo(String memberNo, String movieNo) {
		super();
		this.memberNo = memberNo;
		this.movieNo = movieNo;
	}
	
	public ReviewVo(String memberNo, String movieNo, String content, String rate) {
		super();
		this.memberNo = memberNo;
		this.movieNo = movieNo;
		this.content = content;
		this.rate = rate;
	}

	public String getReviewNo() {
		return reviewNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public String getMovieNo() {
		return movieNo;
	}

	public String getContent() {
		return content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public String getRate() {
		return rate;
	}

	public String getDelYn() {
		return delYn;
	}

	@Override
	public String toString() {
		return "ReviewVo [reviewNo=" + reviewNo + ", memberNo=" + memberNo + ", movieNo=" + movieNo + ", content="
				+ content + ", writeDate=" + writeDate + ", rate=" + rate + ", delYn=" + delYn + "]";
	}
	
	
}

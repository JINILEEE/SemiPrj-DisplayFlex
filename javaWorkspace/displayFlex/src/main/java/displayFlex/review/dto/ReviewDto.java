package displayFlex.review.dto;

public class ReviewDto {
	private String reviewNo;			//리뷰번호
	private String writerNo;				// 작성자 번호
	private String memberNick	;		//작성자 닉네임
	private String content;				//내용
	private String writeDate;			//작성일자
	private String rate;					//평점
	private boolean ableToWatch;	//내가 작성한 리뷰 or 관리자?

	public ReviewDto(String reviewNo, String writerNo, String memberNick, String content, String writeDate, String rate) {
		super();
		this.reviewNo = reviewNo;
		this.writerNo = writerNo;
		this.memberNick = memberNick;
		this.content = content;
		this.writeDate = writeDate;
		this.rate = rate;
		this.ableToWatch = false;
	}

	public String getWriterNo() {
		return writerNo;
	}

	public String getReviewNo() {
		return reviewNo;
	}

	public String getMemberNick() {
		return memberNick;
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
	
	public boolean isAbleToWatch() {
		return ableToWatch;
	}
	
	public void setAbleToWatch(boolean ableToWatch) {
		this.ableToWatch = ableToWatch;
	}

	@Override
	public String toString() {
		return " {\"reviewNo\": \"" + reviewNo + "\", \"writerNo\": \""+ writerNo +"\", \"memberNick\": \"" + memberNick + "\", \"content\": \"" + content+ "\", \"writeDate\": \"" + writeDate + "\", \"rate\": \"" + rate + "\", \"ableToWatch\": "+ableToWatch +"}";
	}
	
}

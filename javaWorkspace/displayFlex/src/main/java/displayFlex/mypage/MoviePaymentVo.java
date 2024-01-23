package displayFlex.mypage;

public class MoviePaymentVo {

	private String paymentsNo;
	private String memberNo;
	private String paymentDate;
	private String price;
	private String movieName;
	
	public String getPaymentsNo() {
		return paymentsNo;
	}
	public void setPaymentsNo(String paymentsNo) {
		this.paymentsNo = paymentsNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public MoviePaymentVo(String paymentsNo, String memberNo, String paymentDate, String price, String movieName) {
		super();
		this.paymentsNo = paymentsNo;
		this.memberNo = memberNo;
		this.paymentDate = paymentDate;
		this.price = price;
		this.movieName = movieName;
	}
	public MoviePaymentVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MoviePaymentVo [paymentsNo=" + paymentsNo + ", memberNo=" + memberNo + ", paymentDate=" + paymentDate
				+ ", price=" + price + ", movieName=" + movieName + "]";
	}
	
	
}


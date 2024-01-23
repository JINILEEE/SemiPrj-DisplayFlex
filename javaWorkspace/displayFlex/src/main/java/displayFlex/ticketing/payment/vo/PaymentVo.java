package displayFlex.ticketing.payment.vo;

import java.util.Arrays;

public class PaymentVo {

	private String memberNo;
	private String selectedMovie;
	private String selectedMovieNo;
	private String posterImg;
	private String selectedDate;
	private String selectedTime;
	private String selectedTimeNo;
	private String selectedTheater;
	private String[] selectedSeat;
	private String totalReserved;
	private String paymentAmount;
	private String discount;
	private String totalAmount;
	private String retainedNo;
	
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getSelectedMovie() {
		return selectedMovie;
	}
	public void setSelectedMovie(String selectedMovie) {
		this.selectedMovie = selectedMovie;
	}
	public String getSelectedMovieNo() {
		return selectedMovieNo;
	}
	public void setSelectedMovieNo(String selectedMovieNo) {
		this.selectedMovieNo = selectedMovieNo;
	}
	public String getPosterImg() {
		return posterImg;
	}
	public void setPosterImg(String posterImg) {
		this.posterImg = posterImg;
	}
	public String getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	public String getSelectedTime() {
		return selectedTime;
	}
	public void setSelectedTime(String selectedTime) {
		this.selectedTime = selectedTime;
	}
	public String getSelectedTimeNo() {
		return selectedTimeNo;
	}
	public void setSelectedTimeNo(String selectedTimeNo) {
		this.selectedTimeNo = selectedTimeNo;
	}
	public String getSelectedTheater() {
		return selectedTheater;
	}
	public void setSelectedTheater(String selectedTheater) {
		this.selectedTheater = selectedTheater;
	}
	public String[] getSelectedSeat() {
		return selectedSeat;
	}
	public void setSelectedSeat(String[] selectedSeat) {
		this.selectedSeat = selectedSeat;
	}
	public String getTotalReserved() {
		return totalReserved;
	}
	public void setTotalReserved(String totalReserved) {
		this.totalReserved = totalReserved;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getRetainedNo() {
		return retainedNo;
	}
	public void setRetainedNo(String retainedNo) {
		this.retainedNo = retainedNo;
	}
	public PaymentVo(String memberNo, String selectedMovie, String selectedMovieNo, String posterImg,
			String selectedDate, String selectedTime, String selectedTimeNo, String selectedTheater,
			String[] selectedSeat, String totalReserved, String paymentAmount, String discount, String totalAmount,
			String retainedNo) {
		super();
		this.memberNo = memberNo;
		this.selectedMovie = selectedMovie;
		this.selectedMovieNo = selectedMovieNo;
		this.posterImg = posterImg;
		this.selectedDate = selectedDate;
		this.selectedTime = selectedTime;
		this.selectedTimeNo = selectedTimeNo;
		this.selectedTheater = selectedTheater;
		this.selectedSeat = selectedSeat;
		this.totalReserved = totalReserved;
		this.paymentAmount = paymentAmount;
		this.discount = discount;
		this.totalAmount = totalAmount;
		this.retainedNo = retainedNo;
	}
	public PaymentVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaymentVo [memberNo=" + memberNo + ", selectedMovie=" + selectedMovie + ", selectedMovieNo="
				+ selectedMovieNo + ", posterImg=" + posterImg + ", selectedDate=" + selectedDate + ", selectedTime="
				+ selectedTime + ", selectedTimeNo=" + selectedTimeNo + ", selectedTheater=" + selectedTheater
				+ ", selectedSeat=" + Arrays.toString(selectedSeat) + ", totalReserved=" + totalReserved
				+ ", paymentAmount=" + paymentAmount + ", discount=" + discount + ", totalAmount=" + totalAmount
				+ ", retainedNo=" + retainedNo + "]";
	}
	public PaymentVo(String selectedMovie, String selectedMovieNo, String posterImg, String selectedDate,
			String selectedTime, String selectedTimeNo, String selectedTheater, String[] selectedSeat,
			String totalReserved, String paymentAmount, String discount, String totalAmount, String retainedNo) {
		super();
		this.selectedMovie = selectedMovie;
		this.selectedMovieNo = selectedMovieNo;
		this.posterImg = posterImg;
		this.selectedDate = selectedDate;
		this.selectedTime = selectedTime;
		this.selectedTimeNo = selectedTimeNo;
		this.selectedTheater = selectedTheater;
		this.selectedSeat = selectedSeat;
		this.totalReserved = totalReserved;
		this.paymentAmount = paymentAmount;
		this.discount = discount;
		this.totalAmount = totalAmount;
		this.retainedNo = retainedNo;
	}
	
	
	
}

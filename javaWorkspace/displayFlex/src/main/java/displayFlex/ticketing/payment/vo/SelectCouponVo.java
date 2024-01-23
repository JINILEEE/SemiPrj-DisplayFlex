package displayFlex.ticketing.payment.vo;

public class SelectCouponVo {

	private String retainedNo;
	private String memberNo;
	private String couponNo;
	private String name;
	private String discount;
	private String couponEnddate;
	public String getRetainedNo() {
		return retainedNo;
	}
	public void setRetainedNo(String retainedNo) {
		this.retainedNo = retainedNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getCouponEnddate() {
		return couponEnddate;
	}
	public void setCouponEnddate(String couponEnddate) {
		this.couponEnddate = couponEnddate;
	}
	public SelectCouponVo(String retainedNo, String memberNo, String couponNo, String name, String discount,
			String couponEnddate) {
		super();
		this.retainedNo = retainedNo;
		this.memberNo = memberNo;
		this.couponNo = couponNo;
		this.name = name;
		this.discount = discount;
		this.couponEnddate = couponEnddate;
	}
	
	

	public SelectCouponVo(String retainedNo, String couponNo, String name, String discount) {
		super();
		this.retainedNo = retainedNo;
		this.couponNo = couponNo;
		this.name = name;
		this.discount = discount;
	}
	public SelectCouponVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SelectCouponVo [retainedNo=" + retainedNo + ", memberNo=" + memberNo + ", couponNo=" + couponNo
				+ ", name=" + name + ", discount=" + discount + ", couponEnddate=" + couponEnddate + "]";
	}
	
	

}

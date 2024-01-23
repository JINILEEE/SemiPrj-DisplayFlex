package displayFlex.mypage;

public class CouponVo {
	
	private String retainedNo;
	private String memberNo;
	private String couponNo;
	private String retainedDate;
	private String couponStatus;
	private String couponEnddate;
	private String couponDelete;
	private String name;
	
	public String getRetainedNo() {
		return retainedNo;
	}
	public void setRetainedNo(String retainedNo) {
		this.retainedNo = retainedNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getRetainedDate() {
		return retainedDate;
	}
	public void setRetainedDate(String retainedDate) {
		this.retainedDate = retainedDate;
	}
	public String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	public String getCouponEnddate() {
		return couponEnddate;
	}
	public void setCouponEnddate(String couponEnddate) {
		this.couponEnddate = couponEnddate;
	}
	public String getCouponDelete() {
		return couponDelete;
	}
	public void setCouponDelete(String couponDelete) {
		this.couponDelete = couponDelete;
	}
	public CouponVo(String retainedNo, String memberNo, String couponNo, String retainedDate, String couponStatus,
			String couponEnddate, String couponDelete, String name) {
		super();
		this.retainedNo = retainedNo;
		this.memberNo = memberNo;
		this.couponNo = couponNo;
		this.retainedDate = retainedDate;
		this.couponStatus = couponStatus;
		this.couponEnddate = couponEnddate;
		this.couponDelete = couponDelete;
		this.name = name;
	}
	public CouponVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CouponVo [retainedNo=" + retainedNo + ", memberNo=" + memberNo + ", couponNo=" + couponNo
				+ ", retainedDate=" + retainedDate + ", couponStatus=" + couponStatus + ", couponEnddate="
				+ couponEnddate + ", couponDelete=" + couponDelete + "]";
	}
	
}

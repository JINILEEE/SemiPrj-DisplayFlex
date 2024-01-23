package displayFlex.coupon.vo;

public class CouponVo {
	
	private String couponNo;
	private String couponType;
	private String couponDiscount;
	private String couponName;
	private String couponCreationDate;
	private String couponValidePeriod;
	private String couponInfo;
	private String couponStatus;
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(String couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponCreationDate() {
		return couponCreationDate;
	}
	public void setCouponCreationDate(String couponCreationDate) {
		this.couponCreationDate = couponCreationDate;
	}
	public String getCouponValidePeriod() {
		return couponValidePeriod;
	}
	public void setCouponValidePeriod(String couponValidePeriod) {
		this.couponValidePeriod = couponValidePeriod;
	}
	public String getCouponInfo() {
		return couponInfo;
	}
	public void setCouponInfo(String couponInfo) {
		this.couponInfo = couponInfo;
	}
	public String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	public CouponVo(String couponNo, String couponType, String couponDiscount, String couponName,
			String couponCreationDate, String couponValidePeriod, String couponInfo, String couponStatus) {
		super();
		this.couponNo = couponNo;
		this.couponType = couponType;
		this.couponDiscount = couponDiscount;
		this.couponName = couponName;
		this.couponCreationDate = couponCreationDate;
		this.couponValidePeriod = couponValidePeriod;
		this.couponInfo = couponInfo;
		this.couponStatus = couponStatus;
	}
	@Override
	public String toString() {
		return "CouponVo [couponNo=" + couponNo + ", couponType=" + couponType + ", couponDiscount=" + couponDiscount
				+ ", couponName=" + couponName + ", couponCreationDate=" + couponCreationDate + ", couponValidePeriod="
				+ couponValidePeriod + ", couponInfo=" + couponInfo + ", couponStatus=" + couponStatus + "]";
	}
	public CouponVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

	

}

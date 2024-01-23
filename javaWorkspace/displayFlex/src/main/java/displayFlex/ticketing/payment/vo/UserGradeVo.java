package displayFlex.ticketing.payment.vo;

public class UserGradeVo {
//	PREVILEGED_YN 장애인여부
//	REGISTER_NO 주민번호
//  GRADE_NO 회원등급번호
	private String gradeNo;
	private String previlegedYn;
	private String age;
	
	public String getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(String gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getPrevilegedYn() {
		return previlegedYn;
	}
	public void setPrevilegedYn(String previlegedYn) {
		this.previlegedYn = previlegedYn;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public UserGradeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserGradeVo(String gradeNo, String previlegedYn, String age) {
		super();
		this.gradeNo = gradeNo;
		this.previlegedYn = previlegedYn;
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserGradeVo [gradeNo=" + gradeNo + ", previlegedYn=" + previlegedYn + ", age=" + age + "]";
	}
	
	
	
	
}

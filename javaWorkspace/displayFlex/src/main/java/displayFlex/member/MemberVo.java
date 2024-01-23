package displayFlex.member;

public class MemberVo {
	
	public String memberNo;
	public String memberId;
	public String memberName;
	public String memberPwd;
	public String gradeNo;
	public String adminYn;
	public String memberNick;
	public String memberEmail;
	public String memberPhoneNum;
	public String enrolldate;
	public String deleteYn;
	public String privilegedYn;
	public String registerNo;
	public String gradeScore;
	
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(String gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getAdminYn() {
		return adminYn;
	}
	public void setAdminYn(String adminYn) {
		this.adminYn = adminYn;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPhoneNum() {
		return memberPhoneNum;
	}
	public void setMemberPhoneNum(String memberPhoneNum) {
		this.memberPhoneNum = memberPhoneNum;
	}
	public String getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(String enrolldate) {
		this.enrolldate = enrolldate;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getPrivilegedYn() {
		return privilegedYn;
	}
	public void setPrivilegedYn(String privilegedYn) {
		this.privilegedYn = privilegedYn;
	}
	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
	public String getGradeScore() {
		return gradeScore;
	}
	public void setGradeScore(String gradeScore) {
		this.gradeScore = gradeScore;
	}
	public MemberVo(String memberNo, String memberId, String memberName, String memberPwd, String gradeNo,
			String adminYn, String memberNick, String memberEmail, String memberPhoneNum, String enrolldate,
			String deleteYn, String privilegedYn, String registerNo, String gradeScore) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPwd = memberPwd;
		this.gradeNo = gradeNo;
		this.adminYn = adminYn;
		this.memberNick = memberNick;
		this.memberEmail = memberEmail;
		this.memberPhoneNum = memberPhoneNum;
		this.enrolldate = enrolldate;
		this.deleteYn = deleteYn;
		this.privilegedYn = privilegedYn;
		this.registerNo = registerNo;
		this.gradeScore = gradeScore;
	}
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MemberVo [memberNo=" + memberNo + ", memberId=" + memberId + ", memberName=" + memberName
				+ ", memberPwd=" + memberPwd + ", gradeNo=" + gradeNo + ", adminYn=" + adminYn + ", memberNick="
				+ memberNick + ", memberEmail=" + memberEmail + ", memberPhoneNum=" + memberPhoneNum + ", enrolldate="
				+ enrolldate + ", deleteYn=" + deleteYn + ", privilegedYn=" + privilegedYn + ", registerNo="
				+ registerNo + ", gradeScore=" + gradeScore + "]";
	}
	
}

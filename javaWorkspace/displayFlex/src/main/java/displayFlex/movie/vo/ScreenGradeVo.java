package displayFlex.movie.vo;

public class ScreenGradeVo {

	private String screenGradeNo;
	private String name;
	
	public ScreenGradeVo(String screenGradeNo, String name) {
		super();
		this.screenGradeNo = screenGradeNo;
		this.name = name;
	}

	public String getScreenGradeNo() {
		return screenGradeNo;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "screenGradeVo [screenGradeNo=" + screenGradeNo + ", name=" + name + "]";
	}
}

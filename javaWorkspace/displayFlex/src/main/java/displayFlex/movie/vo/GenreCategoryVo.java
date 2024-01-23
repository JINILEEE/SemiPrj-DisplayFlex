package displayFlex.movie.vo;

public class GenreCategoryVo {

	private String genreCateNo;
	private String cateName;
	
	public GenreCategoryVo(String genreCateNo, String cateName) {
		super();
		this.genreCateNo = genreCateNo;
		this.cateName = cateName;
	}

	public String getGenreCateNo() {
		return genreCateNo;
	}

	public String getCateName() {
		return cateName;
	}

	@Override
	public String toString() {
		return "GenreCategoryVo [genreCateNo=" + genreCateNo + ", cateName=" + cateName + "]";
	}
	
}

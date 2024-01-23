package displayFlex.movie.vo;

public class StillImageVo {
	private String stillNo;		//스틸 이미지 번호
	private String movieNo;		//영화 번호
	private String filePath;		//파일 경로
	
	public StillImageVo(String stillNo, String movieNo, String filePath) {
		super();
		this.stillNo = stillNo;
		this.movieNo = movieNo;
		this.filePath = filePath;
	}

	public String getStillNo() {
		return stillNo;
	}

	public String getMovieNo() {
		return movieNo;
	}

	public String getFilePath() {
		return filePath;
	}

	@Override
	public String toString() {
		return "StillImageVo [stillNo=" + stillNo + ", movieNo=" + movieNo + ", filePath=" + filePath + "]";
	}
	
	
}

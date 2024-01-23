package displayFlex.serviceCenter.inquiry.dto;

public class UpdateDto {
	private String no;
	private String recontent;
	
	public String getRecontent() {
		return recontent;
	}

	public UpdateDto(String no, String recontent) {
		super();
		this.no = no;
		this.recontent = recontent;
	}

	public String getNo() {
		return no;
	}


	@Override
	public String toString() {
		return "UpdateDto [no=" + no + ", reContent=" + recontent + "]";
	}
	
}

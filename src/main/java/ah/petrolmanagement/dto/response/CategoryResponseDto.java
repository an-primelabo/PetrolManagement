package ah.petrolmanagement.dto.response;

public class CategoryResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = -7091076222676667249L;

	private Integer id;
	private String categoryName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
package ah.petrolmanagement.dto.request;

public class CategoryRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -8532139449068548853L;

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
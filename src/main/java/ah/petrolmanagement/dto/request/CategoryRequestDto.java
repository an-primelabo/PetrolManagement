package ah.petrolmanagement.dto.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class CategoryRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -8532139449068548853L;

	public static final String ID = "id";
	public static final String CATEGORY_NAME = "categoryName";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
package ah.petrolmanagement.dto.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ProductResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = -4022111795725142777L;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer categoryId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String productName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
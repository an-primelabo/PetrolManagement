package ah.petrolmanagement.dto.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ProductRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = 6310069911937819036L;

	public static final String ID = "id";
	public static final String CATEGORY_ID = "categoryId";
	public static final String PRODUCT_NAME = "productName";

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
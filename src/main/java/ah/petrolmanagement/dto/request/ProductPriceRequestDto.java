package ah.petrolmanagement.dto.request;

public class ProductPriceRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -6229527059510434287L;

	private Integer id;
	private Integer categoryId;
	private Integer productId;
	private Integer price;

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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
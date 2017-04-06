package ah.petrolmanagement.dto.request;

public class ProductRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = 6310069911937819036L;

	public static final String PRICE_NEWEST = "priceNewest";

	private Integer id;
	private Integer categoryId;
	private String productName;
	private Integer priceId;
	private Integer price;
	private boolean updateProduct;
	private boolean updatePrice;

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

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public boolean isUpdateProduct() {
		return updateProduct;
	}

	public void setUpdateProduct(boolean updateProduct) {
		this.updateProduct = updateProduct;
	}

	public boolean isUpdatePrice() {
		return updatePrice;
	}

	public void setUpdatePrice(boolean updatePrice) {
		this.updatePrice = updatePrice;
	}
}
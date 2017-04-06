package ah.petrolmanagement.dto.response;

public class ProductPriceResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = -3600290904237187974L;

	private Integer id;
	private Integer productId;
	private String productName;
	private Integer price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setData() {
		String product = "";// ProductManager.getProduct(getProductId());
		setProductName(product);
	}
}
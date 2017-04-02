package ah.petrolmanagement.entity;

public class ProductPriceEntity extends CommonEntity {
	private static final long serialVersionUID = -626757701240559022L;

	private Integer id;
	private Integer productId;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductPriceEntity [id=" + id + ", productId=" + productId
				+ ", price=" + price + "]";
	}
}
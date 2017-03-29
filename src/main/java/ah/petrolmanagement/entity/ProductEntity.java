package ah.petrolmanagement.entity;

public class ProductEntity extends CommonEntity {
	private static final long serialVersionUID = 5198132511786075109L;

	private Integer id;
	private Integer categoryId;
	private String productName;

	public Integer getId() {
		return id;
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

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", categoryId=" + categoryId
				+ ", productName=" + productName + "]";
	}
}
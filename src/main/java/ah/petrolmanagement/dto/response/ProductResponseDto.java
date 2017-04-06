package ah.petrolmanagement.dto.response;

import java.util.Date;

public class ProductResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = -4022111795725142777L;

	private Integer id;
	private Integer categoryId;
	private String productName;
	private Integer priceId;
	private Integer price;
	private Date priceNewest;

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

	public Date getPriceNewest() {
		return priceNewest;
	}

	public void setPriceNewest(Date priceNewest) {
		this.priceNewest = priceNewest;
	}
}
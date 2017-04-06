package ah.petrolmanagement.dto.request;

import java.util.List;

public class ProductPriceRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -6229527059510434287L;

	public static final String ID = "id";
	public static final String PRODUCT_ID = "productId";
	public static final String PRICE = "price";
	public static final String PRODUCT_ID_LIST = "productIdList";
	public static final String SELECT_TOP = "selectTop";

	private Integer id;
	private Integer productId;
	private Integer price;
	private List<Integer> productIdList;
	private Integer selectTop;

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

	public List<Integer> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<Integer> productIdList) {
		this.productIdList = productIdList;
	}

	public Integer getSelectTop() {
		return selectTop;
	}

	public void setSelectTop(Integer selectTop) {
		this.selectTop = selectTop;
	}
}
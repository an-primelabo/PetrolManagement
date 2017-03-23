package ah.petrolmanagement.dto.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class TankRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -2183410580261289633L;

	public static final String ID = "id";
	public static final String PRODUCT_ID = "productId";
	public static final String TANK_NAME = "tankName";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer productId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String tankName;

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

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}
}
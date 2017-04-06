package ah.petrolmanagement.dto.response;

public class TankResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = 3898509463505539267L;

	private Integer id;
	private Integer productId;
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
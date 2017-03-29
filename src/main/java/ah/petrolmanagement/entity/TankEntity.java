package ah.petrolmanagement.entity;

public class TankEntity extends CommonEntity {
	private static final long serialVersionUID = -3649120123929466833L;

	private Integer id;
	private Integer productId;
	private String tankName;

	public Integer getId() {
		return id;
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

	@Override
	public String toString() {
		return "TankEntity [id=" + id + ", productId=" + productId
				+ ", tankName=" + tankName + "]";
	}
}
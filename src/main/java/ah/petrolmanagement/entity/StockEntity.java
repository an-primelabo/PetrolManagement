package ah.petrolmanagement.entity;

public class StockEntity extends CommonEntity {
	private static final long serialVersionUID = 2262580586318690017L;

	private Integer id;
	private Integer tankId;
	private Float openStock;
	private Float receipt;
	private Float dipping;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTankId() {
		return tankId;
	}

	public void setTankId(Integer tankId) {
		this.tankId = tankId;
	}

	public Float getOpenStock() {
		return openStock;
	}

	public void setOpenStock(Float openStock) {
		this.openStock = openStock;
	}

	public Float getReceipt() {
		return receipt;
	}

	public void setReceipt(Float receipt) {
		this.receipt = receipt;
	}

	public Float getDipping() {
		return dipping;
	}

	public void setDipping(Float dipping) {
		this.dipping = dipping;
	}

	@Override
	public String toString() {
		return "StockEntity [id=" + id + ", tankId=" + tankId + ", openStock="
				+ openStock + ", receipt=" + receipt + ", dipping=" + dipping
				+ "]";
	}
}
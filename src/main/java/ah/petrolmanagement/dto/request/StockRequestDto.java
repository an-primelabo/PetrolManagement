package ah.petrolmanagement.dto.request;

public class StockRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -1679817479529686701L;

	public static final String ID = "id";
	public static final String TANK_ID = "tankId";
	public static final String OPEN_STOCK = "openStock";
	public static final String RECEIPT = "receipt";
	public static final String DIPPING = "dipping";

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
}
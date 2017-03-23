package ah.petrolmanagement.dto.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class StockRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -1679817479529686701L;

	public static final String ID = "id";
	public static final String TANK_ID = "tankId";
	public static final String OPEN_STOCK = "openStock";
	public static final String RECEIPT = "receipt";
	public static final String DIPPING = "dipping";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer tankId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float openStock;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float receipt;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
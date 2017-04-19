package ah.petrolmanagement.dto.response;


public class DailyMeterResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = -7622193904343043465L;

	private Integer id;
	private Integer tankId;
	private String tankName;
	private Integer productId;
	private Integer shift;
	private String shiftName;
	private Integer priceId;
	private Integer price;
	private Float meterNew;
	private Float meterOld;
	private Float meterElecNew;
	private Float meterElecOld;

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

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getShift() {
		return shift;
	}

	public void setShift(Integer shift) {
		this.shift = shift;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
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

	public Float getMeterNew() {
		return meterNew;
	}

	public void setMeterNew(Float meterNew) {
		this.meterNew = meterNew;
	}

	public Float getMeterOld() {
		return meterOld;
	}

	public void setMeterOld(Float meterOld) {
		this.meterOld = meterOld;
	}

	public Float getMeterElecNew() {
		return meterElecNew;
	}

	public void setMeterElecNew(Float meterElecNew) {
		this.meterElecNew = meterElecNew;
	}

	public Float getMeterElecOld() {
		return meterElecOld;
	}

	public void setMeterElecOld(Float meterElecOld) {
		this.meterElecOld = meterElecOld;
	}
}
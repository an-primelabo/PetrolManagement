package ah.petrolmanagement.dto.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import ah.petrolmanagement.enums.Shifts;
import ah.petrolmanagement.manager.ProductPriceManager;
import ah.petrolmanagement.manager.TankManager;
import ah.petrolmanagement.utils.Utils;

public class DailyMeterResponseDto extends CommonResponseDto {
	private static final long serialVersionUID = -7622193904343043465L;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer tankId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer productId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String tankName;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer shift;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String shiftName;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer priceId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterOld;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterNew;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterQuantity;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterElecOld;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterElecNew;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterElecQuantity;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer price;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float amount;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float amountElec;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float different;

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

	public Float getMeterOld() {
		return meterOld;
	}

	public void setMeterOld(Float meterOld) {
		this.meterOld = meterOld;
	}

	public Float getMeterNew() {
		return meterNew;
	}

	public void setMeterNew(Float meterNew) {
		this.meterNew = meterNew;
	}

	public Float getMeterQuantity() {
		return meterQuantity;
	}

	public void setMeterQuantity(Float meterQuantity) {
		this.meterQuantity = meterQuantity;
	}

	public Float getMeterElecOld() {
		return meterElecOld;
	}

	public void setMeterElecOld(Float meterElecOld) {
		this.meterElecOld = meterElecOld;
	}

	public Float getMeterElecNew() {
		return meterElecNew;
	}

	public void setMeterElecNew(Float meterElecNew) {
		this.meterElecNew = meterElecNew;
	}

	public Float getMeterElecQuantity() {
		return meterElecQuantity;
	}

	public void setMeterElecQuantity(Float meterElecQuantity) {
		this.meterElecQuantity = meterElecQuantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getAmountElec() {
		return amountElec;
	}

	public void setAmountElec(Float amountElec) {
		this.amountElec = amountElec;
	}

	public Float getDifferent() {
		return different;
	}

	public void setDifferent(Float different) {
		this.different = different;
	}

	public void setData() {
		String tank = TankManager.getTankName(getTankId());
		setTankName(tank);

		Integer product = TankManager.getProductId(getTankId());
		setProductId(product);

		String shift = Shifts.getByCode(getShift());
		setShiftName(shift);

		Float mNew = (getMeterNew() != null) ? getMeterNew() : 0L;
		Float mOld = (getMeterOld() != null) ? getMeterOld() : 0L;
		setMeterQuantity(Utils.roundQuantity(mNew - mOld));

		Float mElecNew = (getMeterElecNew() != null) ? getMeterElecNew() : 0L;
		Float mElecOld = (getMeterElecOld() != null) ? getMeterElecOld() : 0L;
		setMeterElecQuantity(Utils.roundQuantity(mElecNew - mElecOld));

		Integer price = ProductPriceManager.getPrice(getPriceId());
		setPrice(price);

		Float total = getMeterQuantity() * getPrice();
		setAmount(Utils.roundQuantity(total));

		Float totalElec = getMeterElecQuantity() * getPrice();
		setAmountElec(Utils.roundQuantity(totalElec));

		Float diff = getAmountElec() - getAmount();
		setDifferent(Utils.roundQuantity(diff));
	}
}
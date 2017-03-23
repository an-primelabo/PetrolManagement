package ah.petrolmanagement.dto.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class DailyMeterRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -3631533596571093703L;

	public static final String ID = "id";
	public static final String TANK_ID = "tankId";
	public static final String SHIFT_ID = "shiftId";
	public static final String PRICE_ID = "priceId";
	public static final String METER_OLD = "meterOld";
	public static final String METER_NEW = "meterNew";
	public static final String METER_ELEC_OLD = "meterElecOld";
	public static final String METER_ELEC_NEW = "meterElecNew";

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer id;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer tankId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer shiftId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Integer priceId;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterOld;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterNew;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterElecOld;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Float meterElecNew;

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

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
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
}
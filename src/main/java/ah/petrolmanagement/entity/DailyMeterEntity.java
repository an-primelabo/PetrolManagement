package ah.petrolmanagement.entity;

public class DailyMeterEntity extends CommonEntity {
	private static final long serialVersionUID = 2080570196222429443L;

	private Integer id;
	private Integer tankId;
	private Integer shift;
	private Integer priceId;
	private Float meterOld;
	private Float meterNew;
	private Float meterElecOld;
	private Float meterElecNew;

	public Integer getId() {
		return id;
	}

	public Integer getTankId() {
		return tankId;
	}

	public void setTankId(Integer tankId) {
		this.tankId = tankId;
	}

	public Integer getShift() {
		return shift;
	}

	public void setShift(Integer shift) {
		this.shift = shift;
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

	@Override
	public String toString() {
		return "DailyMeterEntity [id=" + id + ", tankId=" + tankId
				+ ", priceId=" + priceId + ", meterOld=" + meterOld
				+ ", meterNew=" + meterNew + ", meterElecOld=" + meterElecOld
				+ ", meterElecNew=" + meterElecNew + "]";
	}
}
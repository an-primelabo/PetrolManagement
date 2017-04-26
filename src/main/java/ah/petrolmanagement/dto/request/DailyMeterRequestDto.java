package ah.petrolmanagement.dto.request;

import java.util.Date;
import java.util.List;

public class DailyMeterRequestDto extends CommonRequestDto {
	private static final long serialVersionUID = -3631533596571093703L;

	public static final String SHIFT = "shift";
	public static final String METER_OLD = "meterOld";
	public static final String METER_NEW = "meterNew";
	public static final String METER_ELEC_OLD = "meterElecOld";
	public static final String METER_ELEC_NEW = "meterElecNew";
	public static final String IS_CHECKED = "isChecked";
	public static final String DAILY_LIST = "dailyList";
	public static final String DATE_FROM = "dateFrom";
	public static final String DATE_TO = "dateTo";
	public static final String MONTH_FROM = "monthFrom";
	public static final String MONTH_TO = "monthTo";

	private Integer id;
	private Integer tankId;
	private Integer shift;
	private Integer priceId;
	private Float meterOld;
	private Float meterNew;
	private Float meterElecOld;
	private Float meterElecNew;
	private Integer isChecked;
	private List<DailyMeterRequestDto> dailyList;
	private Date dateFrom;
	private Date dateTo;
	private Date monthFrom;
	private Date monthTo;

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

	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}

	public List<DailyMeterRequestDto> getDailyList() {
		return dailyList;
	}

	public void setDailyList(List<DailyMeterRequestDto> dailyList) {
		this.dailyList = dailyList;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Date getMonthFrom() {
		return monthFrom;
	}

	public void setMonthFrom(Date monthFrom) {
		this.monthFrom = monthFrom;
	}

	public Date getMonthTo() {
		return monthTo;
	}

	public void setMonthTo(Date monthTo) {
		this.monthTo = monthTo;
	}
}
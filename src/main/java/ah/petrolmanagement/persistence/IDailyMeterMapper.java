package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.DailyMeterEntity;

public interface IDailyMeterMapper {
	List<Map<String, Object>> select(@Param("map") Map<String, Object> map);

	void save(@Param("dailyList") List<DailyMeterEntity> dailyList);

	void update(@Param("entity") DailyMeterEntity entity);

	void delete(@Param("entity") DailyMeterEntity entity);
}
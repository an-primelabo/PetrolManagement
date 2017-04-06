package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.DailyMeterEntity;

public interface IDailyMeterMapper {
	List<DailyMeterEntity> select(@Param("map") Map<String, Object> map);

	void save(@Param("entity") DailyMeterEntity entity);

	void update(@Param("entity") DailyMeterEntity entity);

	void delete(@Param("entity") DailyMeterEntity entity);
}
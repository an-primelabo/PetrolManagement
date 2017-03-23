package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.DailyMeterEntity;

public interface IDailyMeterMapper {
	List<DailyMeterEntity> select(final @Param("map") Map<String, Object> map);

	void save(final @Param("entity") DailyMeterEntity entity);

	void update(final @Param("entity") DailyMeterEntity entity);

	void delete(final @Param("entity") DailyMeterEntity entity);
}
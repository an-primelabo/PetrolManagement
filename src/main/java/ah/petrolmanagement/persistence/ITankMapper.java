package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.TankEntity;

public interface ITankMapper {
	List<TankEntity> select(@Param("map") Map<String, Object> map);

	void save(@Param("entity") TankEntity entity);

	void update(@Param("entity") TankEntity entity);

	void delete(@Param("entity") TankEntity entity);
}
package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.TankEntity;

public interface ITankMapper {
	List<TankEntity> select(final @Param("map") Map<String, Object> map);

	void save(final @Param("entity") TankEntity entity);

	void update(final @Param("entity") TankEntity entity);

	void delete(final @Param("entity") TankEntity entity);
}
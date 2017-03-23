package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.ShiftEntity;

public interface IShiftMapper {
	List<ShiftEntity> select(final @Param("map") Map<String, Object> map);

	void save(final @Param("entity") ShiftEntity entity);

	void update(final @Param("entity") ShiftEntity entity);

	void delete(final @Param("entity") ShiftEntity entity);
}
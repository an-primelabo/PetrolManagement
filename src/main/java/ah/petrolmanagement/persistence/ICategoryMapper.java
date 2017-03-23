package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.CategoryEntity;

public interface ICategoryMapper {
	List<CategoryEntity> select(final @Param("map") Map<String, Object> map);

	void save(final @Param("entity") CategoryEntity entity);

	void update(final @Param("entity") CategoryEntity entity);

	void delete(final @Param("entity") CategoryEntity entity);
}
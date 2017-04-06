package ah.petrolmanagement.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.CategoryEntity;

public interface ICategoryMapper {
	List<CategoryEntity> select();

	void save(@Param("entity") CategoryEntity entity);

	void update(@Param("entity") CategoryEntity entity);

	void delete(@Param("entity") CategoryEntity entity);
}
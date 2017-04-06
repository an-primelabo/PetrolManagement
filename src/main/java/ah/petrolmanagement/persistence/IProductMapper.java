package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.ProductEntity;

public interface IProductMapper {
	List<Map<String, Object>> selectByCategoryId(@Param("categoryId") Integer categoryId);

	void save(@Param("entity") ProductEntity entity);

	void update(@Param("entity") ProductEntity entity);

	void delete(@Param("entity") ProductEntity entity);
}
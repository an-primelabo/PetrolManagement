package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.ProductEntity;

public interface IProductMapper {
	List<ProductEntity> select(final @Param("map") Map<String, Object> map);

	void save(final @Param("entity") ProductEntity entity);

	void update(final @Param("entity") ProductEntity entity);

	void delete(final @Param("entity") ProductEntity entity);
}
package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.ProductPriceEntity;

public interface IProductPriceMapper {
	List<ProductPriceEntity> select(@Param("map") Map<String, Object> map);

	List<ProductPriceEntity> selectPrice(@Param("map") Map<String, Object> map);

	List<ProductPriceEntity> selectOldPrice(@Param("productIdList") List<Integer> productIdList);

	List<ProductPriceEntity> selectNewPrice(@Param("productIdList") List<Integer> productIdList);

	void save(@Param("entity") ProductPriceEntity entity);

	void update(final @Param("entity") ProductPriceEntity entity);

	void delete(final @Param("entity") ProductPriceEntity entity);
}
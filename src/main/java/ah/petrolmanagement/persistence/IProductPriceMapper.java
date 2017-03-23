package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.ProductPriceEntity;

public interface IProductPriceMapper {
	List<ProductPriceEntity> select(final @Param("map") Map<String, Object> map);

	List<ProductPriceEntity> selectPrice(final @Param("productId") Integer productId);

	List<ProductPriceEntity> selectOldPrice(final @Param("productIdList") List<Integer> productIdList);

	List<ProductPriceEntity> selectNewPrice(final @Param("productIdList") List<Integer> productIdList);

	void save(final @Param("entity") ProductPriceEntity entity);

	void update(final @Param("entity") ProductPriceEntity entity);

	void delete(final @Param("entity") ProductPriceEntity entity);
}
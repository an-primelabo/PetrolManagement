package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.ProductPriceEntity;

public interface IProductPriceMapper {
	List<Map<String, Object>> selectNewestPrice(
			@Param("categoryId") Integer categoryId);

	void save(@Param("entity") ProductPriceEntity entity);

	void update(final @Param("entity") ProductPriceEntity entity);
}
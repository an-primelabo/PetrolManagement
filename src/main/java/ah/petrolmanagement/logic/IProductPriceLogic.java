package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;

public interface IProductPriceLogic {
	List<ProductPriceResponseDto> selectNewestPrice(
			final ProductPriceRequestDto request) throws Exception;

	ProductPriceResponseDto save(final ProductPriceRequestDto request)
			throws Exception;
}
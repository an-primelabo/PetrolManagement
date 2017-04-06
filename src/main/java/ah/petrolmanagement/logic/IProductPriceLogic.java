package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;

public interface IProductPriceLogic {
	List<ProductPriceResponseDto> select(final ProductPriceRequestDto request)
			throws Exception;

	List<ProductPriceResponseDto> selectPrice(
			final ProductPriceRequestDto request) throws Exception;

	List<ProductPriceResponseDto> selectOldPrice(
			final ProductPriceRequestDto request) throws Exception;

	List<ProductPriceResponseDto> selectNewPrice(
			final ProductPriceRequestDto request) throws Exception;

	ProductPriceResponseDto save(final ProductPriceRequestDto request)
			throws Exception;

	ProductPriceResponseDto update(final ProductPriceRequestDto request)
			throws Exception;

	ProductPriceResponseDto delete(final ProductPriceRequestDto request)
			throws Exception;
}
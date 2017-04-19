package ah.petrolmanagement.service;

import java.util.List;

import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;

public interface IProductPriceService {
	public List<ProductPriceResponseDto> selectNewestPrice(
			final ProductPriceRequestDto request) throws Exception;

	public ProductPriceResponseDto save(final ProductPriceRequestDto request)
			throws Exception;
}
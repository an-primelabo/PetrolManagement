package ah.petrolmanagement.service;

import java.util.List;

import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;

public interface IProductPriceService {
	public List<ProductPriceResponseDto> select(
			final ProductPriceRequestDto request) throws Exception;

	public List<ProductPriceResponseDto> selectPrice(
			final ProductPriceRequestDto request) throws Exception;

	public List<ProductPriceResponseDto> selectOldPrice(
			final ProductPriceRequestDto request) throws Exception;

	public List<ProductPriceResponseDto> selectNewPrice(
			final ProductPriceRequestDto request) throws Exception;

	public ProductPriceResponseDto save(final ProductPriceRequestDto request)
			throws Exception;

	public ProductPriceResponseDto update(final ProductPriceRequestDto request)
			throws Exception;

	public ProductPriceResponseDto delete(final ProductPriceRequestDto request)
			throws Exception;
}
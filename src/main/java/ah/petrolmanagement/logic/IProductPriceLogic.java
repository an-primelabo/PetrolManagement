package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public interface IProductPriceLogic {
	List<ProductPriceResponseDto> select(final ProductPriceRequestDto dto)
			throws PetrolException;

	List<ProductPriceResponseDto> selectPrice(final ProductPriceRequestDto dto)
			throws PetrolException;

	List<ProductPriceResponseDto> selectOldPrice(
			final ProductPriceRequestDto dto) throws PetrolException;

	List<ProductPriceResponseDto> selectNewPrice(
			final ProductPriceRequestDto dto) throws PetrolException;

	ProductPriceResponseDto save(final ProductPriceRequestDto dto)
			throws PetrolException;

	ProductPriceResponseDto update(final ProductPriceRequestDto dto)
			throws PetrolException;

	ProductPriceResponseDto delete(final ProductPriceRequestDto dto)
			throws PetrolException;
}
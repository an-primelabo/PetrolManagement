package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.ProductRequestDto;
import ah.petrolmanagement.dto.response.ProductResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public interface IProductLogic {
	List<ProductResponseDto> select(final ProductRequestDto dto)
			throws PetrolException;

	ProductResponseDto save(final ProductRequestDto dto) throws PetrolException;

	ProductResponseDto update(final ProductRequestDto dto)
			throws PetrolException;

	ProductResponseDto delete(final ProductRequestDto dto)
			throws PetrolException;
}
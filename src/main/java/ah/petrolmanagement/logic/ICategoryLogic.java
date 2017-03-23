package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.CategoryRequestDto;
import ah.petrolmanagement.dto.response.CategoryResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public interface ICategoryLogic {
	List<CategoryResponseDto> select(final CategoryRequestDto dto)
			throws PetrolException;

	CategoryResponseDto save(final CategoryRequestDto dto)
			throws PetrolException;

	CategoryResponseDto update(final CategoryRequestDto dto)
			throws PetrolException;

	CategoryResponseDto delete(final CategoryRequestDto dto)
			throws PetrolException;
}
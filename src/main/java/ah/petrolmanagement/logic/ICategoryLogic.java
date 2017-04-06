package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.CategoryRequestDto;
import ah.petrolmanagement.dto.response.CategoryResponseDto;

public interface ICategoryLogic {
	List<CategoryResponseDto> select() throws Exception;

	CategoryResponseDto save(final CategoryRequestDto request) throws Exception;

	CategoryResponseDto update(final CategoryRequestDto request)
			throws Exception;

	CategoryResponseDto delete(final CategoryRequestDto request)
			throws Exception;
}
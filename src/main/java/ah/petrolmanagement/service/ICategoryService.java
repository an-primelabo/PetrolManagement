package ah.petrolmanagement.service;

import java.util.List;

import ah.petrolmanagement.dto.request.CategoryRequestDto;
import ah.petrolmanagement.dto.response.CategoryResponseDto;

public interface ICategoryService {
	public List<CategoryResponseDto> select() throws Exception;

	public CategoryResponseDto save(final CategoryRequestDto request)
			throws Exception;

	public CategoryResponseDto update(final CategoryRequestDto request)
			throws Exception;

	public CategoryResponseDto delete(final CategoryRequestDto request)
			throws Exception;
}
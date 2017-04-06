package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.ProductRequestDto;
import ah.petrolmanagement.dto.response.ProductResponseDto;

public interface IProductLogic {
	List<ProductResponseDto> selectByCategoryId(final ProductRequestDto request)
			throws Exception;

	ProductResponseDto save(final ProductRequestDto request) throws Exception;

	ProductResponseDto update(final ProductRequestDto request) throws Exception;

	ProductResponseDto delete(final ProductRequestDto request) throws Exception;
}
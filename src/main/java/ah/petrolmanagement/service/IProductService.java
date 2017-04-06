package ah.petrolmanagement.service;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;

import ah.petrolmanagement.dto.request.ProductRequestDto;
import ah.petrolmanagement.dto.response.ProductResponseDto;

public interface IProductService {
	public List<ProductResponseDto> selectByCategoryId(
			final ProductRequestDto request) throws Exception;

	public @ResponseBody ProductResponseDto save(final ProductRequestDto request)
			throws Exception;

	public @ResponseBody ProductResponseDto update(
			final ProductRequestDto request) throws Exception;

	public @ResponseBody ProductResponseDto delete(
			final ProductRequestDto request) throws Exception;
}
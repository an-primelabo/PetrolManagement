package ah.petrolmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.petrolmanagement.dto.request.ProductRequestDto;
import ah.petrolmanagement.dto.response.ProductResponseDto;
import ah.petrolmanagement.logic.IProductLogic;
import ah.petrolmanagement.service.IProductService;

@Service("productService")
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductLogic logic;

	@Override
	public List<ProductResponseDto> selectByCategoryId(ProductRequestDto request)
			throws Exception {
		return logic.selectByCategoryId(request);
	}

	@Override
	public ProductResponseDto save(ProductRequestDto request) throws Exception {
		return logic.save(request);
	}

	@Override
	public ProductResponseDto update(ProductRequestDto request)
			throws Exception {
		return logic.update(request);
	}

	@Override
	public ProductResponseDto delete(ProductRequestDto request)
			throws Exception {
		return logic.delete(request);
	}
}
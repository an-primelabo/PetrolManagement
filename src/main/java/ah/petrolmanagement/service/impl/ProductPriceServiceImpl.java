package ah.petrolmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;
import ah.petrolmanagement.logic.IProductPriceLogic;
import ah.petrolmanagement.service.IProductPriceService;

@Service("priceService")
public class ProductPriceServiceImpl implements IProductPriceService {
	@Autowired
	private IProductPriceLogic logic;

	@Override
	public List<ProductPriceResponseDto> selectNewestPrice(
			ProductPriceRequestDto request) throws Exception {
		return logic.selectNewestPrice(request);
	}

	@Override
	public ProductPriceResponseDto save(ProductPriceRequestDto request)
			throws Exception {
		return logic.save(request);
	}
}
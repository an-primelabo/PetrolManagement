package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;
import ah.petrolmanagement.entity.ProductPriceEntity;
import ah.petrolmanagement.logic.IProductPriceLogic;
import ah.petrolmanagement.persistence.IProductPriceMapper;
import ah.petrolmanagement.utils.LogUtil;
import ah.petrolmanagement.utils.Utils;

@Component
public class ProductPriceLogicImpl implements IProductPriceLogic {
	@Autowired
	private IProductPriceMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<ProductPriceResponseDto> selectNewestPrice(
			final ProductPriceRequestDto request) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "select", request);

		List<Map<String, Object>> entities = mapper.selectNewestPrice(request
				.getCategoryId());
		return setData(entities);
	}

	@Override
	public ProductPriceResponseDto save(final ProductPriceRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "save", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductPriceEntity entitySave = setDataEntity(request);
		entitySave.setId(null);
		entitySave.setUpdUser(null);

		ProductPriceEntity entityUpdate = setDataEntity(request);
		entityUpdate.setInsUser(null);
		ProductPriceResponseDto response = new ProductPriceResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.save(entitySave);
			mapper.update(entityUpdate);
		} catch (Exception e) {
			LogUtil.errorLog(this.getClass().getSimpleName(), "save error", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(request);

			if (e instanceof DuplicateKeyException) {
				response.setErrorsList(new String[] { ApiConstants.ERR_ITEM_DUPLICATE });
			} else {
				response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			}
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	private List<ProductPriceResponseDto> setData(
			List<Map<String, Object>> entities) {
		List<ProductPriceResponseDto> list = new ArrayList<ProductPriceResponseDto>();

		for (Map<String, Object> entity : entities) {
			ProductPriceResponseDto response = new ProductPriceResponseDto();
			response.setId(Utils.getInteger(entity
					.get(ProductPriceRequestDto.ID)));
			response.setProductId(Utils.getInteger(entity
					.get(ProductPriceRequestDto.PRODUCT_ID)));
			response.setProductName(Utils.getString(entity
					.get(ProductPriceRequestDto.PRODUCT_NAME)));
			response.setPrice(Utils.getInteger(entity
					.get(ProductPriceRequestDto.PRICE)));
			response.setInsTime((Date) entity
					.get(ProductPriceRequestDto.INS_TIME));
			response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(response);
		}
		return list;
	}

	private ProductPriceEntity setDataEntity(ProductPriceRequestDto request) {
		ProductPriceEntity entity = new ProductPriceEntity();
		BeanUtils.copyProperties(request, entity);
		return entity;
	}

	private ProductPriceResponseDto setDataResponse(
			ProductPriceRequestDto request) {
		ProductPriceResponseDto response = new ProductPriceResponseDto();
		BeanUtils.copyProperties(request, response);
		return response;
	}
}
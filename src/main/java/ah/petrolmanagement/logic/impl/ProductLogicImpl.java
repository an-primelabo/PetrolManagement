package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.dto.request.ProductRequestDto;
import ah.petrolmanagement.dto.response.ProductResponseDto;
import ah.petrolmanagement.entity.ProductEntity;
import ah.petrolmanagement.entity.ProductPriceEntity;
import ah.petrolmanagement.logic.IProductLogic;
import ah.petrolmanagement.persistence.IProductMapper;
import ah.petrolmanagement.persistence.IProductPriceMapper;
import ah.petrolmanagement.utils.LogUtil;
import ah.petrolmanagement.utils.Utils;

@Component
public class ProductLogicImpl implements IProductLogic {
	@Autowired
	private IProductMapper mapper;

	@Autowired
	private IProductPriceMapper priceMapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<ProductResponseDto> selectByCategoryId(final ProductRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "select", request);

		List<Map<String, Object>> entities = mapper.selectByCategoryId(request.getCategoryId());
		return setData(entities);
	}

	@Override
	public ProductResponseDto save(final ProductRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "save", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductEntity entity = setDataEntity(request);
		ProductPriceEntity priceEntity = setDataPriceEntity(request);
		ProductResponseDto response = new ProductResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.save(entity);
			priceEntity.setProductId(entity.getId());
			priceMapper.save(priceEntity);
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

	@Override
	public ProductResponseDto update(final ProductRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "update", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductEntity entity = setDataEntity(request);
		ProductResponseDto response = new ProductResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.update(entity);
		} catch (Exception e) {
			LogUtil.errorLog(this.getClass().getSimpleName(), "update error", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(request);
			response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public ProductResponseDto delete(final ProductRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "delete", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductEntity entity = setDataEntity(request);
		ProductResponseDto response = new ProductResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.delete(entity);
		} catch (Exception e) {
			LogUtil.errorLog(this.getClass().getSimpleName(), "delete error", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(request);
			response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	private Map<String, Object> setDataMap(ProductRequestDto request) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (request.getId() != null) {
			map.put(ProductRequestDto.ID, request.getId());
		}
		if (request.getCategoryId() != null) {
			map.put(ProductRequestDto.CATEGORY_ID, request.getCategoryId());
		}
		if (StringUtils.isNotBlank(request.getProductName())) {
			map.put(ProductRequestDto.PRODUCT_NAME, request.getProductName());
		}
		return map;
	}

	private List<ProductResponseDto> setData(List<Map<String, Object>> entities) {
		List<ProductResponseDto> list = new ArrayList<ProductResponseDto>();

		for (Map<String, Object> entity : entities) {
			ProductResponseDto response = new ProductResponseDto();
			response.setId(Utils.getInteger(entity.get(ProductRequestDto.ID)));
			response.setCategoryId(Utils.getInteger(entity.get(ProductRequestDto.CATEGORY_ID)));
			response.setProductName(Utils.getString(entity.get(ProductRequestDto.PRODUCT_NAME)));
			response.setPriceId(Utils.getInteger(entity.get(ProductRequestDto.PRICE_ID)));
			response.setPrice(Utils.getInteger(entity.get(ProductRequestDto.PRICE)));
			response.setPriceNewest((Date) entity.get(ProductRequestDto.PRICE_NEWEST));
			response.setInsTime((Date) entity.get(ProductRequestDto.INS_TIME));
			response.setInsUser(Utils.getString(entity.get(ProductRequestDto.INS_USER)));
			response.setUpdTime((Date) entity.get(ProductRequestDto.UPD_TIME));
			response.setUpdUser(Utils.getString(entity.get(ProductRequestDto.UPD_USER)));
			response.setDelFlag(Utils.getInteger(entity.get(ProductRequestDto.DEL_FLAG)));
			response.setDelTime((Date) entity.get(ProductRequestDto.DEL_TIME));
			response.setDelUser(Utils.getString(entity.get(ProductRequestDto.DEL_USER)));
			response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(response);
		}
		return list;
	}

	private ProductEntity setDataEntity(ProductRequestDto request) {
		ProductEntity entity = new ProductEntity();
		BeanUtils.copyProperties(request, entity);
		return entity;
	}

	private ProductPriceEntity setDataPriceEntity(ProductRequestDto request) {
		ProductPriceEntity entity = new ProductPriceEntity();
		entity.setProductId(request.getId());
		entity.setPrice(request.getPrice());

		if (StringUtils.equals(request.getMode(), ApiConstants.INSERT)) {
			entity.setInsUser(request.getInsUser());
		} else if (StringUtils.equals(request.getMode(), ApiConstants.UPDATE)) {
			entity.setId(request.getPriceId());
			entity.setUpdUser(request.getUpdUser());
		}
		return entity;
	}

	private ProductResponseDto setDataResponse(ProductRequestDto request) {
		ProductResponseDto response = new ProductResponseDto();
		BeanUtils.copyProperties(request, response);
		return response;
	}
}
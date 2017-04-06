package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
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

@Component
public class ProductPriceLogicImpl implements IProductPriceLogic {
	@Autowired
	private IProductPriceMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<ProductPriceResponseDto> select(
			final ProductPriceRequestDto request) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "select", request);

		Map<String, Object> map = setDataMap(request);
		List<ProductPriceEntity> entities = mapper.select(map);
		List<ProductPriceResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public List<ProductPriceResponseDto> selectPrice(
			final ProductPriceRequestDto request) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "selectPrice",
				request);

		List<ProductPriceResponseDto> list = new ArrayList<ProductPriceResponseDto>();

//		for (Integer productId : request.getProductIdList()) {
//			ProductPriceRequestDto request = new ProductPriceRequestDto();
//			request.setProductId(productId);
//			request.setSelectTop(request.getSelectTop());
//
//			Map<String, Object> map = setDataMap(request);
//			List<ProductPriceEntity> entities = mapper.selectPrice(map);
//			list.addAll(setData(entities));
//		}
		return list;
	}

	@Override
	public List<ProductPriceResponseDto> selectOldPrice(
			final ProductPriceRequestDto request) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "selectOldPrice",
				request);

		List<ProductPriceEntity> entities = mapper.selectOldPrice(request
				.getProductIdList());
		List<ProductPriceResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public List<ProductPriceResponseDto> selectNewPrice(
			final ProductPriceRequestDto request) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "selectNewPrice",
				request);

		List<ProductPriceEntity> entities = mapper.selectNewPrice(request
				.getProductIdList());
		List<ProductPriceResponseDto> list = setData(entities);
		return list;
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

	@Override
	public ProductPriceResponseDto update(final ProductPriceRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "update", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductPriceEntity entity = setDataEntity(request);
		ProductPriceResponseDto response = new ProductPriceResponseDto();

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
	public ProductPriceResponseDto delete(final ProductPriceRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "delete", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductPriceEntity entity = setDataEntity(request);
		ProductPriceResponseDto response = new ProductPriceResponseDto();

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

	private Map<String, Object> setDataMap(ProductPriceRequestDto request) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (request.getId() != null) {
			map.put(ProductPriceRequestDto.ID, request.getId());
		}
		if (request.getProductId() != null) {
			map.put(ProductPriceRequestDto.PRODUCT_ID, request.getProductId());
		}
		if (request.getPrice() != null) {
			map.put(ProductPriceRequestDto.PRICE, request.getPrice());
		}
		if (request.getSelectTop() != null) {
			map.put(ProductPriceRequestDto.SELECT_TOP, request.getSelectTop());
		}
		return map;
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

	private List<ProductPriceResponseDto> setData(
			List<ProductPriceEntity> entities) {
		List<ProductPriceResponseDto> list = new ArrayList<ProductPriceResponseDto>();

		for (ProductPriceEntity entity : entities) {
			ProductPriceResponseDto response = new ProductPriceResponseDto();
			BeanUtils.copyProperties(entity, response);
			response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(response);
		}
		return list;
	}
}
package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.logic.IProductLogic;
import ah.petrolmanagement.persistence.IProductMapper;

@Component
public class ProductLogicImpl extends CommonLogic implements IProductLogic {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IProductMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<ProductResponseDto> select(final ProductRequestDto dto) {
		logger.info("select : {}", dto);

		Map<String, Object> map = setDataMap(dto);
		List<ProductEntity> entities = mapper.select(map);
		List<ProductResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public ProductResponseDto save(final ProductRequestDto dto) {
		logger.info("save : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductEntity entity = setDataEntity(dto);
		ProductResponseDto response = new ProductResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.save(entity);
		} catch (Exception e) {
			logger.error("save error : {}", e);
			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(dto);

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
	public ProductResponseDto update(final ProductRequestDto dto) {
		logger.info("update : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductEntity entity = setDataEntity(dto);
		ProductResponseDto response = new ProductResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.update(entity);
		} catch (Exception e) {
			logger.error("update error : {}", e);
			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(dto);
			response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public ProductResponseDto delete(final ProductRequestDto dto) {
		logger.info("delete : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductEntity entity = setDataEntity(dto);
		ProductResponseDto response = new ProductResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.delete(entity);
		} catch (Exception e) {
			logger.error("delete error : {}", e);
			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(dto);
			response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	private Map<String, Object> setDataMap(ProductRequestDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getId() != null) {
			map.put(ProductRequestDto.ID, dto.getId());
		}
		if (dto.getCategoryId() != null) {
			map.put(ProductRequestDto.CATEGORY_ID, dto.getCategoryId());
		}
		if (StringUtils.isNotBlank(dto.getProductName())) {
			map.put(ProductRequestDto.PRODUCT_NAME, dto.getProductName());
		}
		return map;
	}

	private ProductEntity setDataEntity(ProductRequestDto dto) {
		ProductEntity entity = new ProductEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private ProductResponseDto setDataResponse(ProductRequestDto dto) {
		ProductResponseDto response = new ProductResponseDto();
		BeanUtils.copyProperties(dto, response);
		return response;
	}

	private List<ProductResponseDto> setData(List<ProductEntity> entities) {
		List<ProductResponseDto> list = new ArrayList<ProductResponseDto>();

		for (ProductEntity entity : entities) {
			ProductResponseDto dto = new ProductResponseDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(dto);
		}
		return list;
	}
}
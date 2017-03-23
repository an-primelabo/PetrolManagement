package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;
import ah.petrolmanagement.entity.ProductPriceEntity;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.logic.IProductPriceLogic;
import ah.petrolmanagement.persistence.IProductPriceMapper;

@Component
public class ProductPriceLogicImpl extends CommonLogic implements IProductPriceLogic {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IProductPriceMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<ProductPriceResponseDto> select(final ProductPriceRequestDto dto) {
		logger.info("select : {}", dto);

		Map<String, Object> map = setDataMap(dto);
		List<ProductPriceEntity> entities = mapper.select(map);
		List<ProductPriceResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public List<ProductPriceResponseDto> selectPrice(final ProductPriceRequestDto dto) throws PetrolException {
		logger.info("selectPrice : {}", dto);

		List<ProductPriceEntity> entities = mapper.selectPrice(dto.getProductId());
		List<ProductPriceResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public List<ProductPriceResponseDto> selectOldPrice(final ProductPriceRequestDto dto) throws PetrolException {
		logger.info("selectOldPrice : {}", dto);

		List<ProductPriceEntity> entities = mapper.selectOldPrice(dto.getProductIdList());
		List<ProductPriceResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public List<ProductPriceResponseDto> selectNewPrice(final ProductPriceRequestDto dto) throws PetrolException {
		logger.info("selectNewPrice : {}", dto);

		List<ProductPriceEntity> entities = mapper.selectNewPrice(dto.getProductIdList());
		List<ProductPriceResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public ProductPriceResponseDto save(final ProductPriceRequestDto dto) {
		logger.info("save : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductPriceEntity entity = setDataEntity(dto);
		ProductPriceResponseDto response = new ProductPriceResponseDto();

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
	public ProductPriceResponseDto update(final ProductPriceRequestDto dto) {
		logger.info("update : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductPriceEntity entity = setDataEntity(dto);
		ProductPriceResponseDto response = new ProductPriceResponseDto();

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
	public ProductPriceResponseDto delete(final ProductPriceRequestDto dto) {
		logger.info("delete : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ProductPriceEntity entity = setDataEntity(dto);
		ProductPriceResponseDto response = new ProductPriceResponseDto();

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

	private Map<String, Object> setDataMap(ProductPriceRequestDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getId() != null) {
			map.put(ProductPriceRequestDto.ID, dto.getId());
		}
		if (dto.getProductId() != null) {
			map.put(ProductPriceRequestDto.PRODUCT_ID, dto.getProductId());
		}
		if (dto.getPrice() != null) {
			map.put(ProductPriceRequestDto.PRICE, dto.getPrice());
		}
		return map;
	}

	private ProductPriceEntity setDataEntity(ProductPriceRequestDto dto) {
		ProductPriceEntity entity = new ProductPriceEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private ProductPriceResponseDto setDataResponse(ProductPriceRequestDto dto) {
		ProductPriceResponseDto response = new ProductPriceResponseDto();
		BeanUtils.copyProperties(dto, response);
		return response;
	}

	private List<ProductPriceResponseDto> setData(
			List<ProductPriceEntity> entities) {
		List<ProductPriceResponseDto> list = new ArrayList<ProductPriceResponseDto>();

		for (ProductPriceEntity entity : entities) {
			ProductPriceResponseDto dto = new ProductPriceResponseDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(dto);
		}
		return list;
	}
}
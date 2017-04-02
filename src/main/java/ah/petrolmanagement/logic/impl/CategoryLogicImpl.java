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
import ah.petrolmanagement.dto.request.CategoryRequestDto;
import ah.petrolmanagement.dto.response.CategoryResponseDto;
import ah.petrolmanagement.entity.CategoryEntity;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.logic.ICategoryLogic;
import ah.petrolmanagement.persistence.ICategoryMapper;

@Component
public class CategoryLogicImpl extends CommonLogic implements ICategoryLogic {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICategoryMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<CategoryResponseDto> select(final CategoryRequestDto dto)
			throws PetrolException {
		logger.info("select : {}", dto);

		Map<String, Object> map = setDataMap(dto);
		List<CategoryEntity> entities = mapper.select(map);
		List<CategoryResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public CategoryResponseDto save(final CategoryRequestDto dto)
			throws PetrolException {
		logger.info("save : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		CategoryEntity entity = setDataEntity(dto);
		CategoryResponseDto response = new CategoryResponseDto();

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
		response = setDataResponse(dto);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public CategoryResponseDto update(final CategoryRequestDto dto)
			throws PetrolException {
		logger.info("update : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		CategoryEntity entity = setDataEntity(dto);
		CategoryResponseDto response = new CategoryResponseDto();

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
		response = setDataResponse(dto);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public CategoryResponseDto delete(final CategoryRequestDto dto)
			throws PetrolException {
		logger.info("delete : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		CategoryEntity entity = setDataEntity(dto);
		CategoryResponseDto response = new CategoryResponseDto();

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
		response = setDataResponse(dto);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	private Map<String, Object> setDataMap(CategoryRequestDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getId() != null) {
			map.put(CategoryRequestDto.ID, dto.getId());
		}
		if (StringUtils.isNotBlank(dto.getCategoryName())) {
			map.put(CategoryRequestDto.CATEGORY_NAME, dto.getCategoryName());
		}
		return map;
	}

	private CategoryEntity setDataEntity(CategoryRequestDto dto) {
		CategoryEntity entity = new CategoryEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private CategoryResponseDto setDataResponse(CategoryRequestDto dto) {
		CategoryResponseDto response = new CategoryResponseDto();
		BeanUtils.copyProperties(dto, response);
		return response;
	}

	private List<CategoryResponseDto> setData(List<CategoryEntity> entities) {
		List<CategoryResponseDto> list = new ArrayList<CategoryResponseDto>();

		for (CategoryEntity entity : entities) {
			CategoryResponseDto dto = new CategoryResponseDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(dto);
		}
		return list;
	}
}
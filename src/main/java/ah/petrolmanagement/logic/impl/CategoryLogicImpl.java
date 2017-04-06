package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;

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
import ah.petrolmanagement.logic.ICategoryLogic;
import ah.petrolmanagement.persistence.ICategoryMapper;
import ah.petrolmanagement.utils.LogUtil;

@Component
public class CategoryLogicImpl implements ICategoryLogic {
	@Autowired
	private ICategoryMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<CategoryResponseDto> select() throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "select");

		List<CategoryEntity> entities = mapper.select();
		return setData(entities);
	}

	@Override
	public CategoryResponseDto save(final CategoryRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "save", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		CategoryEntity entity = setDataEntity(request);
		CategoryResponseDto response = new CategoryResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.save(entity);
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
	public CategoryResponseDto update(final CategoryRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "update", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		CategoryEntity entity = setDataEntity(request);
		CategoryResponseDto response = new CategoryResponseDto();

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
	public CategoryResponseDto delete(final CategoryRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "delete", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		CategoryEntity entity = setDataEntity(request);
		CategoryResponseDto response = new CategoryResponseDto();

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

	private CategoryResponseDto setData(CategoryEntity entity) {
		CategoryResponseDto response = new CategoryResponseDto();
		BeanUtils.copyProperties(entity, response);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	private List<CategoryResponseDto> setData(List<CategoryEntity> entities) {
		List<CategoryResponseDto> list = new ArrayList<CategoryResponseDto>();

		for (CategoryEntity entity : entities) {
			CategoryResponseDto response = setData(entity);

			list.add(response);
		}
		return list;
	}

	private CategoryEntity setDataEntity(CategoryRequestDto request) {
		CategoryEntity entity = new CategoryEntity();
		BeanUtils.copyProperties(request, entity);
		return entity;
	}

	private CategoryResponseDto setDataResponse(CategoryRequestDto request) {
		CategoryResponseDto response = new CategoryResponseDto();
		BeanUtils.copyProperties(request, response);
		return response;
	}
}
package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
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
import ah.petrolmanagement.dto.request.TankRequestDto;
import ah.petrolmanagement.dto.response.TankResponseDto;
import ah.petrolmanagement.entity.TankEntity;
import ah.petrolmanagement.logic.ITankLogic;
import ah.petrolmanagement.persistence.ITankMapper;
import ah.petrolmanagement.utils.LogUtil;

@Component
public class TankLogicImpl implements ITankLogic {
	@Autowired
	private ITankMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<TankResponseDto> selectFuelTanks() throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "selectFuelTanks");

		List<TankEntity> entities = mapper.selectFuelTanks();
		return setData(entities);
	}

	@Override
	public List<TankResponseDto> select(final TankRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "select", request);

		Map<String, Object> map = setDataMap(request);
		List<TankEntity> entities = mapper.select(map);
		List<TankResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public TankResponseDto save(final TankRequestDto request) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "save", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		TankEntity entity = setDataEntity(request);
		TankResponseDto response = new TankResponseDto();

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
		response = setDataResponse(request);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public TankResponseDto update(final TankRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "update", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		TankEntity entity = setDataEntity(request);
		TankResponseDto response = new TankResponseDto();

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
		response = setDataResponse(request);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public TankResponseDto delete(final TankRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "delete", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		TankEntity entity = setDataEntity(request);
		TankResponseDto response = new TankResponseDto();

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
		response = setDataResponse(request);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	private Map<String, Object> setDataMap(TankRequestDto request) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (request.getId() != null) {
			map.put(TankRequestDto.ID, request.getId());
		}
		if (request.getProductId() != null) {
			map.put(TankRequestDto.PRODUCT_ID, request.getProductId());
		}
		if (StringUtils.isNotBlank(request.getTankName())) {
			map.put(TankRequestDto.TANK_NAME, request.getTankName());
		}
		return map;
	}

	private TankEntity setDataEntity(TankRequestDto request) {
		TankEntity entity = new TankEntity();
		BeanUtils.copyProperties(request, entity);
		return entity;
	}

	private TankResponseDto setDataResponse(TankRequestDto request) {
		TankResponseDto response = new TankResponseDto();
		BeanUtils.copyProperties(request, response);
		return response;
	}

	private List<TankResponseDto> setData(List<TankEntity> entities) {
		List<TankResponseDto> list = new ArrayList<TankResponseDto>();

		for (TankEntity entity : entities) {
			TankResponseDto response = new TankResponseDto();
			BeanUtils.copyProperties(entity, response);
			response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(response);
		}
		return list;
	}
}
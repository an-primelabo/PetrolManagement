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
import ah.petrolmanagement.dto.request.TankRequestDto;
import ah.petrolmanagement.dto.response.TankResponseDto;
import ah.petrolmanagement.entity.TankEntity;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.logic.ITankLogic;
import ah.petrolmanagement.persistence.ITankMapper;

@Component
public class TankLogicImpl extends CommonLogic implements ITankLogic {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITankMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<TankResponseDto> select(final TankRequestDto dto)
			throws PetrolException {
		logger.info("select : {}", dto);

		Map<String, Object> map = setDataMap(dto);
		List<TankEntity> entities = mapper.select(map);
		List<TankResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public TankResponseDto save(final TankRequestDto dto)
			throws PetrolException {
		logger.info("save : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		TankEntity entity = setDataEntity(dto);
		TankResponseDto response = new TankResponseDto();

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
	public TankResponseDto update(final TankRequestDto dto)
			throws PetrolException {
		logger.info("update : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		TankEntity entity = setDataEntity(dto);
		TankResponseDto response = new TankResponseDto();

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
	public TankResponseDto delete(final TankRequestDto dto)
			throws PetrolException {
		logger.info("delete : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		TankEntity entity = setDataEntity(dto);
		TankResponseDto response = new TankResponseDto();

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

	private Map<String, Object> setDataMap(TankRequestDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getId() != null) {
			map.put(TankRequestDto.ID, dto.getId());
		}
		if (dto.getProductId() != null) {
			map.put(TankRequestDto.PRODUCT_ID, dto.getProductId());
		}
		if (StringUtils.isNotBlank(dto.getTankName())) {
			map.put(TankRequestDto.TANK_NAME, dto.getTankName());
		}
		return map;
	}

	private TankEntity setDataEntity(TankRequestDto dto) {
		TankEntity entity = new TankEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private TankResponseDto setDataResponse(TankRequestDto dto) {
		TankResponseDto response = new TankResponseDto();
		BeanUtils.copyProperties(dto, response);
		return response;
	}

	private List<TankResponseDto> setData(List<TankEntity> entities) {
		List<TankResponseDto> list = new ArrayList<TankResponseDto>();

		for (TankEntity entity : entities) {
			TankResponseDto dto = new TankResponseDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(dto);
		}
		return list;
	}
}
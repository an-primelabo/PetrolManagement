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
import ah.petrolmanagement.dto.request.ShiftRequestDto;
import ah.petrolmanagement.dto.response.ShiftResponseDto;
import ah.petrolmanagement.entity.ShiftEntity;
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.logic.IShiftLogic;
import ah.petrolmanagement.persistence.IShiftMapper;

@Component
public class ShiftLogicImpl extends CommonLogic implements IShiftLogic {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IShiftMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<ShiftResponseDto> select(final ShiftRequestDto dto) {
		logger.info("select : {}", dto);

		Map<String, Object> map = setDataMap(dto);
		List<ShiftEntity> entities = mapper.select(map);
		List<ShiftResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public ShiftResponseDto save(final ShiftRequestDto dto) {
		logger.info("save : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ShiftEntity entity = setDataEntity(dto);
		ShiftResponseDto response = new ShiftResponseDto();

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
	public ShiftResponseDto update(final ShiftRequestDto dto) {
		logger.info("update : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ShiftEntity entity = setDataEntity(dto);
		ShiftResponseDto response = new ShiftResponseDto();

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
	public ShiftResponseDto delete(final ShiftRequestDto dto) {
		logger.info("delete : {}", dto);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		ShiftEntity entity = setDataEntity(dto);
		ShiftResponseDto response = new ShiftResponseDto();

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

	private Map<String, Object> setDataMap(ShiftRequestDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getId() != null) {
			map.put(ShiftRequestDto.ID, dto.getId());
		}
		if (dto.getShiftCode() != null) {
			map.put(ShiftRequestDto.SHIFT_CODE, dto.getShiftCode());
		}
		if (StringUtils.isNotBlank(dto.getShiftName())) {
			map.put(ShiftRequestDto.SHIFT_NAME, dto.getShiftName());
		}
		if (StringUtils.isNotBlank(dto.getUsername())) {
			map.put(ShiftRequestDto.USERNAME, dto.getUsername());
		}
		return map;
	}

	private ShiftEntity setDataEntity(ShiftRequestDto dto) {
		ShiftEntity entity = new ShiftEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private ShiftResponseDto setDataResponse(ShiftRequestDto dto) {
		ShiftResponseDto response = new ShiftResponseDto();
		BeanUtils.copyProperties(dto, response);
		return response;
	}

	private List<ShiftResponseDto> setData(List<ShiftEntity> entities) {
		List<ShiftResponseDto> list = new ArrayList<ShiftResponseDto>();

		for (ShiftEntity entity : entities) {
			ShiftResponseDto dto = new ShiftResponseDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(dto);
		}
		return list;
	}
}
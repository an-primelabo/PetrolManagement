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
import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;
import ah.petrolmanagement.entity.DailyMeterEntity;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.logic.IDailyMeterLogic;
import ah.petrolmanagement.persistence.IDailyMeterMapper;

@Component
public class DailyMeterLogicImpl extends CommonLogic implements IDailyMeterLogic {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IDailyMeterMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<DailyMeterResponseDto> select(final DailyMeterRequestDto dto)
			throws PetrolException {
		logger.info("select : {}", dto);

		Map<String, Object> map = setDataMap(dto);
		List<DailyMeterEntity> entities = mapper.select(map);
		List<DailyMeterResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public DailyMeterResponseDto save(final DailyMeterRequestDto dto)
			throws PetrolException {
		logger.info("save : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		List<DailyMeterEntity> list = setDataEntity(dto);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			for (DailyMeterEntity entity : list) {
				mapper.save(entity);
			}
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
	public DailyMeterResponseDto update(final DailyMeterRequestDto dto)
			throws PetrolException {
		logger.info("update : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		List<DailyMeterEntity> list = setDataEntity(dto);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			for (DailyMeterEntity entity : list) {
				mapper.save(entity);
			}
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
	public DailyMeterResponseDto delete(final DailyMeterRequestDto dto)
			throws PetrolException {
		logger.info("delete : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		List<DailyMeterEntity> list = setDataEntity(dto);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			for (DailyMeterEntity entity : list) {
				mapper.delete(entity);
			}
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

	private Map<String, Object> setDataMap(DailyMeterRequestDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getId() != null) {
			map.put(DailyMeterRequestDto.ID, dto.getId());
		}
		if (dto.getTankId() != null) {
			map.put(DailyMeterRequestDto.TANK_ID, dto.getTankId());
		}
		if (dto.getShift() != null) {
			map.put(DailyMeterRequestDto.SHIFT, dto.getShift());
		}
		if (dto.getPriceId() != null) {
			map.put(DailyMeterRequestDto.PRICE_ID, dto.getPriceId());
		}
		if (dto.getMeterOld() != null) {
			map.put(DailyMeterRequestDto.METER_OLD, dto.getMeterOld());
		}
		if (dto.getMeterNew() != null) {
			map.put(DailyMeterRequestDto.METER_NEW, dto.getMeterNew());
		}
		if (dto.getMeterElecOld() != null) {
			map.put(DailyMeterRequestDto.METER_ELEC_OLD, dto.getMeterElecOld());
		}
		if (dto.getMeterElecNew() != null) {
			map.put(DailyMeterRequestDto.METER_ELEC_NEW, dto.getMeterElecNew());
		}
		if (dto.getInsTime() != null) {
			map.put(DailyMeterRequestDto.INS_TIME, dto.getInsTime());
		}
		if (dto.getDateFrom() != null) {
			map.put(DailyMeterRequestDto.DATE_FROM, dto.getDateFrom());
		}
		if (dto.getDateTo() != null) {
			map.put(DailyMeterRequestDto.DATE_TO, dto.getDateTo());
		}
		if (dto.getMonthFrom() != null) {
			map.put(DailyMeterRequestDto.MONTH_FROM, dto.getMonthFrom());
		}
		if (dto.getMonthTo() != null) {
			map.put(DailyMeterRequestDto.MONTH_TO, dto.getMonthTo());
		}
		return map;
	}

	private List<DailyMeterEntity> setDataEntity(DailyMeterRequestDto dto) {
		List<DailyMeterEntity> list = new ArrayList<DailyMeterEntity>();

		for (DailyMeterRequestDto request : dto.getDailyList()) {
			DailyMeterEntity entity = new DailyMeterEntity();
			BeanUtils.copyProperties(request, entity);

			list.add(entity);
		}
		return list;
	}

	private DailyMeterResponseDto setDataResponse(DailyMeterRequestDto dto) {
		DailyMeterResponseDto response = new DailyMeterResponseDto();
		BeanUtils.copyProperties(dto, response);
		return response;
	}

	private List<DailyMeterResponseDto> setData(List<DailyMeterEntity> entities) {
		List<DailyMeterResponseDto> list = new ArrayList<DailyMeterResponseDto>();

		for (DailyMeterEntity entity : entities) {
			DailyMeterResponseDto dto = new DailyMeterResponseDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(dto);
		}
		return list;
	}
}
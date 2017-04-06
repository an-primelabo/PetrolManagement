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
import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;
import ah.petrolmanagement.entity.DailyMeterEntity;
import ah.petrolmanagement.logic.IDailyMeterLogic;
import ah.petrolmanagement.persistence.IDailyMeterMapper;
import ah.petrolmanagement.utils.LogUtil;

@Component
public class DailyMeterLogicImpl implements IDailyMeterLogic {
	@Autowired
	private IDailyMeterMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<DailyMeterResponseDto> select(final DailyMeterRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "select", request);

		Map<String, Object> map = setDataMap(request);
		List<DailyMeterEntity> entities = mapper.select(map);
		List<DailyMeterResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public DailyMeterResponseDto save(final DailyMeterRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "save", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		List<DailyMeterEntity> list = setDataEntity(request);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			for (DailyMeterEntity entity : list) {
				mapper.save(entity);
			}
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
	public DailyMeterResponseDto update(final DailyMeterRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "update", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		List<DailyMeterEntity> list = setDataEntity(request);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			for (DailyMeterEntity entity : list) {
				mapper.save(entity);
			}
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
	public DailyMeterResponseDto delete(final DailyMeterRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "delete", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		List<DailyMeterEntity> list = setDataEntity(request);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			for (DailyMeterEntity entity : list) {
				mapper.delete(entity);
			}
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

	private Map<String, Object> setDataMap(DailyMeterRequestDto request) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (request.getId() != null) {
			map.put(DailyMeterRequestDto.ID, request.getId());
		}
		if (request.getTankId() != null) {
			map.put(DailyMeterRequestDto.TANK_ID, request.getTankId());
		}
		if (request.getShift() != null) {
			map.put(DailyMeterRequestDto.SHIFT, request.getShift());
		}
		if (request.getPriceId() != null) {
			map.put(DailyMeterRequestDto.PRICE_ID, request.getPriceId());
		}
		if (request.getMeterOld() != null) {
			map.put(DailyMeterRequestDto.METER_OLD, request.getMeterOld());
		}
		if (request.getMeterNew() != null) {
			map.put(DailyMeterRequestDto.METER_NEW, request.getMeterNew());
		}
		if (request.getMeterElecOld() != null) {
			map.put(DailyMeterRequestDto.METER_ELEC_OLD,
					request.getMeterElecOld());
		}
		if (request.getMeterElecNew() != null) {
			map.put(DailyMeterRequestDto.METER_ELEC_NEW,
					request.getMeterElecNew());
		}
		if (request.getInsTime() != null) {
			map.put(DailyMeterRequestDto.INS_TIME, request.getInsTime());
		}
		if (request.getDateFrom() != null) {
			map.put(DailyMeterRequestDto.DATE_FROM, request.getDateFrom());
		}
		if (request.getDateTo() != null) {
			map.put(DailyMeterRequestDto.DATE_TO, request.getDateTo());
		}
		if (request.getMonthFrom() != null) {
			map.put(DailyMeterRequestDto.MONTH_FROM, request.getMonthFrom());
		}
		if (request.getMonthTo() != null) {
			map.put(DailyMeterRequestDto.MONTH_TO, request.getMonthTo());
		}
		return map;
	}

	private List<DailyMeterEntity> setDataEntity(DailyMeterRequestDto request) {
		List<DailyMeterEntity> list = new ArrayList<DailyMeterEntity>();

		for (DailyMeterRequestDto daily : request.getDailyList()) {
			DailyMeterEntity entity = new DailyMeterEntity();
			BeanUtils.copyProperties(daily, entity);

			list.add(entity);
		}
		return list;
	}

	private DailyMeterResponseDto setDataResponse(DailyMeterRequestDto request) {
		DailyMeterResponseDto response = new DailyMeterResponseDto();
		BeanUtils.copyProperties(request, response);
		return response;
	}

	private List<DailyMeterResponseDto> setData(List<DailyMeterEntity> entities) {
		List<DailyMeterResponseDto> list = new ArrayList<DailyMeterResponseDto>();

		for (DailyMeterEntity entity : entities) {
			DailyMeterResponseDto response = new DailyMeterResponseDto();
			BeanUtils.copyProperties(entity, response);
			response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(response);
		}
		return list;
	}
}